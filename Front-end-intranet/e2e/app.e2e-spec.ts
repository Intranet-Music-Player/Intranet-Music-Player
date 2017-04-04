import { FrontEndIntranetPage } from './app.po';

describe('front-end-intranet App', function() {
  let page: FrontEndIntranetPage;

  beforeEach(() => {
    page = new FrontEndIntranetPage();
  });

  it('should display message saying app works', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('app works!');
  });
});
