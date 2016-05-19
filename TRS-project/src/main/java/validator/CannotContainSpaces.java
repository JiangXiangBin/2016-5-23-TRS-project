package validator;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = CannotContainSpacesValidator.class)
// �����ʵ��
@Target({ java.lang.annotation.ElementType.METHOD,
		java.lang.annotation.ElementType.FIELD })
@Retention(java.lang.annotation.RetentionPolicy.RUNTIME)
@Documented
public @interface CannotContainSpaces {
	// ��ʾ��Ϣ,����д��,������д���ʻ���key
	String message() default "{�������}";

	int length() default 5;
	
	// �������������Ա������
	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
