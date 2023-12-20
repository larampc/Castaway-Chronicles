package castaway_chronicles.model.mainPage;


public class MainPage {
    private final MainMenu mainMenu;
    private final EndingPage endingPage;
    private PAGE current;
    public MainPage() {
        mainMenu = new MainMenu();
        endingPage = new EndingPageBuilder().createEndingPage();
        current = PAGE.MENU;
    }
    public MainMenu getMainMenu() {
        return mainMenu;
    }
    public EndingPage getEndingPage() {
        return endingPage;
    }
    public PAGE getCurrent() {
        return current;
    }
    public void setCurrent(PAGE current) {
        this.current = current;
    }
    public enum PAGE {MENU, ENDINGS}
}
