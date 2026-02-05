
describe('Checkout Happy Path', () => {
  it('5029 - Applies stacked coupons and completes checkout', () => {
    cy.addItemToCart('Laptop');
    cy.applyCoupon('PERCENT10');
    cy.applyCoupon('FLAT50');
    cy.checkout();
    cy.verifyOrderSuccess();
  });
});
