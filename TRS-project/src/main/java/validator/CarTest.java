package validator;
import static org.junit.Assert.*;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.junit.BeforeClass;
import org.junit.Test;
public class CarTest {

	private static Validator validator;

	@BeforeClass
	public static void setUp() {
		//��һ��ʵ�������֤֮ǰ������Ҫ�и�Validator����,
		//�������������Ҫͨ��Validation ��� ValidatorFactory��������. 
		//��򵥵ķ����ǵ���Validation.buildDefaultValidatorFactory() 
		//�����̬����.

        Validation.buildDefaultValidatorFactory();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void manufacturerIsNull() {
		//�������Ը�ֵ
		Car car = new Car(null, "DD-AB-123", 4);
           /*
            * �������������᷵��һ��Set<ConstraintViolation>����,
            * ���������֤����û�з�������Ļ�,��ô���set�ǿյ�,
            * ����, ÿ��Υ��Լ���ĵط����ᱻ��װ��һ��ConstraintViolation��ʵ��
            * Ȼ����ӵ�set����.���е�У�鷽�����������������������˴�У��
            * �ǻ����ĸ�У����Ĳ���.
            * ���û�и�����������Ļ�, ��ô�˴�У�齫�����Ĭ�ϵ�У���� 
            * (javax.validation.groups.Default)
            * 
            * */ 
		Set<ConstraintViolation<Car>> constraintViolations = validator
				
				.validate(car);

		assertEquals(1, constraintViolations.size());
		
		assertEquals("may not be null", constraintViolations.iterator().next()
				//getmessage�����ǻ�ȡ(���������)У�������Ϣ
				.getMessage());
	}

//	@Test
	public void licensePlateTooShort() {
		Car car = new Car("Morris", "D", 4);

		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);

		assertEquals(1, constraintViolations.size());
		assertEquals("size must be between 2 and 14", constraintViolations
				.iterator().next().getMessage());
	}

//@Test
	public void seatCountTooLow() {
		Car car = new Car("Morris", "DD-AB-123", 1);

		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);

		assertEquals(1, constraintViolations.size());
		assertEquals("must be greater than or equal to 2", constraintViolations
				.iterator().next().getMessage());
	}

	//@Test
	public void carIsValid() {
		
		Car car = new Car("Morris", "DD-AB-123", 2);

		Set<ConstraintViolation<Car>> constraintViolations = validator
				.validate(car);

		assertEquals(0, constraintViolations.size());
	}

}
