package validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CannotContainSpacesValidator implements ConstraintValidator<CannotContainSpaces, String> {    
    private int len;    
    /**  
     * ��ʼ����,��ȡע����length��ֵ  
     */    
    public void initialize(CannotContainSpaces arg0) {    
        this.len = arg0.length();    
    }    
    
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {    
        if(str != null){    
            if(str.indexOf(" ") < 0){    
                return true;    
            }    
        }else{    
            constraintValidatorContext.disableDefaultConstraintViolation();//����Ĭ�ϵ�message��ֵ    
            //������Ӵ�����ʾ���    
            constraintValidatorContext    
            .buildConstraintViolationWithTemplate("�ַ�������Ϊ��").addConstraintViolation();    
        }    
        return false;    
    } 
}
