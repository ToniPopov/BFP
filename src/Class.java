import java.util.ArrayList;

public class Class extends Abstr implements Iface {

	ArrayList<Class> list = new ArrayList<Class>();
	String name;

	Class() {
		this.name = "Toni";
	}

	String tostring() {
		return this.name;
	}

	public void method() {
		System.out.println("HOi");
	}

	public static void main(String[] args) {
		Class i = new Class();
		i.list.add(i);
		i.method();
		System.out.println(i.list.get(0).name);

	}

}
