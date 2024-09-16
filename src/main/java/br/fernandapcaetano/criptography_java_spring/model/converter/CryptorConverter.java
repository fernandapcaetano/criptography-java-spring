package br.fernandapcaetano.criptography_java_spring.model.converter;



import br.fernandapcaetano.criptography_java_spring.util.CryptoUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class CryptorConverter implements AttributeConverter<String,String>{

    @Override
    public String convertToDatabaseColumn(String attribute) {
        try {
            return CryptoUtil.encrypt(attribute);  // Criptografa antes de salvar
        } catch (Exception e) {
            throw new RuntimeException("Erro ao criptografar o campo", e);
        }
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        try {
            return CryptoUtil.decrypt(dbData);  // Descriptografa ao carregar
        } catch (Exception e) {
            throw new RuntimeException("Erro ao descriptografar o campo", e);
        }
    }

   
    
}
