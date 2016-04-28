package by.bsuir.station.bean;

import by.bsuir.station.entity.Purchase;
import by.bsuir.station.entity.Route;
import by.bsuir.station.exceptions.DataRetrieveException;
import by.bsuir.station.factory.PurchaseTemplateFactory;
import by.bsuir.station.factory.RouteTemplateFactory;
import by.bsuir.station.state.DataState;
import by.bsuir.station.state.ErrorState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import java.util.Date;
import java.util.List;

@ManagedBean(name = "purchaseBean")
@Component
public class PurchaseBean extends AbstractBean {
    @Autowired private PurchaseTemplateFactory purchaseTemplateFactory;
    @Autowired private RouteTemplateFactory routeTemplateFactory;
    @Autowired private DataState dataState;

    private List<Purchase> purchases;

    public String bookRoute() {
        Purchase purchase = new Purchase();
        purchase.setRoute(dataState.getRoute());
        purchase.setUser(dataState.getUser());
        purchase.setDatePurchased(new Date());

        try {
            purchase = purchaseTemplateFactory.post(purchase);

            dataState.setRoute(routeTemplateFactory.get(purchase.getRoute().getRouteId()));
        } catch (DataRetrieveException e) {
            setError(ErrorState.SAVE_ERROR);
        }

        return "route";
    }

    public boolean isEditable(Purchase purchase) {
        Date date = new Date();
        return purchase.getRoute().getDateStart().after(date);
    }

    public String removePurchase(Purchase purchase) {
        try {
            purchaseTemplateFactory.delete(purchase.getPurchaseId());
        } catch (DataRetrieveException e) {
            setError(ErrorState.DELETE_ERROR);
        }

        return null;
    }

    public String clearError() {
        setError(null);

        return null;
    }

    public List<Purchase> getPurchases() {
        try {
            purchases = purchaseTemplateFactory.get();
        } catch (DataRetrieveException e) {
            setError(ErrorState.EDIT_ERROR);
        }

        return purchases;
    }

    public List<Purchase> getPurchasesByUser() {
        try {
            purchases = purchaseTemplateFactory.getByUser(dataState.getUser().getUserId());
        } catch (DataRetrieveException e) {
            setError(ErrorState.EDIT_ERROR);
        }

        return purchases;
    }

    public void setPurchases(List<Purchase> purchases) {
        this.purchases = purchases;
    }
}
