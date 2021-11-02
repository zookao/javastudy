package zoo;

public class AnimalCenterTest {

	public static void main(String[] args) throws Exception {
		AnimalCenter ac = BeanFactory.getBean(AnimalCenter.class);
		ac.firstShow();
		ac.secondShow();		
	}

}
