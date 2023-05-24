package bo;

import bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BoTypes types) {
        switch (types) {
           /* case CUSTOMER:
                return new CustomerBOImpl();
            case ITEM:
                return new ItemBOImpl();*/
            case DISCOUNT:
                return new DiscountBOImpl();
            /*case ORDER:
                return new OrderBOImpl();*/
            case ADMINDASHBOARD:
                return new AdminDashBoardBOImpl();
            case ADMINSTOCK:
                return new AdminStockBOImpl();
            case CASHIERCUSTOMERDETAIL:
                return new CashierCustomerDetailBOImpl();
            case CASHIERDASHBOARD:
                return new CashierDashBoardBOImpl();
            case CASHIERORDERDETAILS:
                return new CashierOrderDetailsBOImpl();
            case CASHIERPLACEORDER:
                return new CashierPlaceOrderBOImpl();
            case CASHIERSELL:
                return new CashierSellBOImpl();
            case ADMINREPORT:
                return new AdminReportBOImpl();
            default:
                return null;
        }
    }

    public enum BoTypes {
        ADMINDASHBOARD,ADMINSTOCK,CASHIERCUSTOMERDETAIL,CASHIERDASHBOARD,CASHIERORDERDETAILS,CASHIERPLACEORDER,CASHIERSELL,ADMINREPORT,

        CUSTOMER, ITEM, DISCOUNT, ORDER
    }
}
