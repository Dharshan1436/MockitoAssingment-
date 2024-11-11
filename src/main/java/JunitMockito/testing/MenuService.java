package JunitMockito.testing;

public class MenuService {

	public MenuItem getMenuItem(Long menuItemId) {

		return new MenuItem(menuItemId, "Pizza", 10, 50);
	}

}
