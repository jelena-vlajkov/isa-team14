// private Long id;
// private SupplierDTO supplier;
// private OrderDTO order;
// private OfferStatus offerStatus;
// private int uniqueidentifier;
// private Long price;
// private Date dueDelivery;

import { NumberValueAccessor } from "@angular/forms";
import { OfferStatus } from "./offerStatus";
import { Supplier } from "./supplier";
import {Order} from "@app/model/medicationOrder/order";

export class Offer{
    public id : Number;
    public supplier: Supplier;
    public order :Order;
    public offerStatus : OfferStatus;
    public uniqueidentifier : number;
    public price : Number;
    public dueDelivery : Date;
    public editing : boolean;

    constructor(id : Number, supplier: Supplier, order : Order, offerStatus : OfferStatus, uniqueidentifier : number, price : Number, dueDelivery : Date){

        this.id = id;
        this.supplier = supplier;
        this.order= order;
        this.offerStatus = offerStatus;
        this.uniqueidentifier = uniqueidentifier;
        this.price = price;
        this. dueDelivery = dueDelivery;
        this.editing = false;
    }

}
