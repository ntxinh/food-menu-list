import { FeFoodMenuListPage } from './app.po';

describe('fe-food-menu-list App', () => {
  let page: FeFoodMenuListPage;

  beforeEach(() => {
    page = new FeFoodMenuListPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!');
  });
});
