package zoo;

public class AnimalCenter {
	//需要将注解的值产生实例，然后注入到first变量中
	@Inject(value="zoo.Tiger")
	private Animal first;
	
	//需要将注解的值产生实例，然后注入到second变量中
	@Inject(value="zoo.Bird")
	private Animal second;
	
	public void firstShow()
	{
		first.move();
	}
	
	public void secondShow()
	{
		second.move();
	}
}
