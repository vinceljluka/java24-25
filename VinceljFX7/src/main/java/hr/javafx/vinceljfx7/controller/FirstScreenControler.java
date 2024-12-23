package hr.javafx.vinceljfx7.controller;

import hr.javafx.vinceljfx7.main.RestaurantApplication;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import java.io.IOException;
import java.net.URL;

public class FirstScreenControler {

    public void showSearchCategoryScreen() throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchCategory.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("categorySearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();

    }

    public void showSearchIngredientsScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchIngredient.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("ingredientsSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }

    public void showSearchMealScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchMeal.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("mealSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }

    public void showSearchChefScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchChef.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("chefSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }

    public void showSearchWaiterScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchWaiter.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("waiterSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }

    public void showSearchDelivererScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchDeliverer.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("delivererSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }

    public void showSearchRestaurantScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchRestaurant.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("restaurantSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }

    public void showSearchAndAddContractScreen() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new URL("file:./src/main/resources/hr/javafx/vinceljfx7/searchAndAddContract.fxml"));

        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        RestaurantApplication.getMainStageStage().setTitle("ContractAddAndSearch");
        RestaurantApplication.getMainStageStage().setScene(scene);
        RestaurantApplication.getMainStageStage().show();
    }


}