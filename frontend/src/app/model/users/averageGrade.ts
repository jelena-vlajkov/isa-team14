export class AverageGrade{
  public excellent:Number;
  public veryGood:Number;
  public good:Number;
  public poor:Number;
  public veryPoor:Number;

  count() {
    return ((5 * this.excellent.valueOf()) + (4 * this.veryGood.valueOf()) + (3 * this.good.valueOf())
      + (2 * this.poor.valueOf()) + (1 * this.veryPoor.valueOf()))
      / (this.excellent.valueOf() + this.veryGood.valueOf() + this.good.valueOf()
        + this.poor.valueOf() + this.veryPoor.valueOf());

  }
}
