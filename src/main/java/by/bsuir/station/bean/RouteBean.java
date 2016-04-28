package by.bsuir.station.bean;

import by.bsuir.station.entity.Route;
import by.bsuir.station.exceptions.DataRetrieveException;
import by.bsuir.station.factory.RouteTemplateFactory;
import by.bsuir.station.state.DataState;
import by.bsuir.station.state.ErrorState;
import by.bsuir.station.validator.RouteValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.bean.ManagedBean;
import java.util.List;

@ManagedBean(name = "routeBean")
@Component
public class RouteBean extends AbstractBean {
    @Autowired private RouteTemplateFactory routeTemplateFactory;
    @Autowired private RouteValidator routeValidator;
    @Autowired private DataState dataState;

    private List<Route> routes;
    private Route route;
    private String searchQuery;

    public RouteBean() {
        route = new Route();
    }

    public String routeDetailsPage(Route route) {
        dataState.setRoute(route);
        System.out.println(route.toString());

        return "route";
    }

    public String search() {
        getRoutes();

        return "index";
    }

    public String makeEditable(Route route) {
        editableIds.add(route.getRouteId());
        return null;
    }

    public String post() {
        try {
            if(routeValidator.validate(route)) {
                routeTemplateFactory.post(route);
            } else {
                setError(ErrorState.SAVE_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.SAVE_ERROR);
        }

        route = new Route();

        return "routes";
    }

    public String put(Route route) {
        try {
            if(routeValidator.validate(route)) {
                route = routeTemplateFactory.put(route);
                route.setEditable(false);
                editableIds.remove(route.getRouteId());
            } else {
                setError(ErrorState.EDIT_ERROR);
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.EDIT_ERROR);
        }

        return "routes";
    }

    public String delete(Route route) {
        try {
            routeTemplateFactory.delete(route.getRouteId());
        } catch (DataRetrieveException e) {
            setError(ErrorState.DELETE_ERROR);
        }

        return "routes";
    }

    public String clearError() {
        setError(null);

        return "routes";
    }

    public List<Route> getRoutes() {
        try {
            if(editableIds.size() > 0) {
                for (Route route : routes) {
                    if(editableIds.contains(route.getRouteId())) {
                        route.setEditable(true);
                    } else {
                        route.setEditable(false);
                    }
                }
            } else {
                if(searchQuery == null || searchQuery.length() == 0) {
                    routes = routeTemplateFactory.get();
                } else {
                    routes = routeTemplateFactory.get(searchQuery);
                }
            }
        } catch (DataRetrieveException e) {
            setError(ErrorState.READ_ERROR);
        }

        return routes;
    }

    public void setRoutes(List<Route> routes) {
        this.routes = routes;
    }

    public Route getRoute() {
        return route;
    }

    public void setRoute(Route route) {
        this.route = route;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
