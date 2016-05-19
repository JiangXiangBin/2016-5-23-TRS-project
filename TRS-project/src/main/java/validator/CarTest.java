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
		//对一个实体对象验证之前首先需要有个Validator对象,
		//而这个对象是需要通过Validation 类和 ValidatorFactory来创建的. 
		//最简单的方法是调用Validation.buildDefaultValidatorFactory() 
		//这个静态方法.

        Validation.buildDefaultValidatorFactory();
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void manufacturerIsNull() {
		//给其属性赋值
		Car car = new Car(null, "DD-AB-123", 4);
           /*
            * 这三个方法都会返回一个Set<ConstraintViolation>对象,
            * 如果整个验证过程没有发现问题的话,那么这个set是空的,
            * 否则, 每个违反约束的地方都会被包装成一个ConstraintViolation的实例
            * 然后添加到set当中.所有的校验方法都接收零个或多个用来定义此次校验
            * 是基于哪个校验组的参数.
            * 如果没有给出这个参数的话, 那么此次校验将会基于默认的校验组 
            * (javax.validation.groups.Default)
            * 
            * */ 
		Set<ConstraintViolation<Car>> constraintViolations = validator
				
				.validate(car);

		assertEquals(1, constraintViolations.size());
		
		assertEquals("may not be null", constraintViolations.iterator().next()
				//getmessage（）是获取(经过翻译的)校验错误信息
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
