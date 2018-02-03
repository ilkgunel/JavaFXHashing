package com.ilkaygunel.main;

import com.ilkaygunel.hashcalculation.AbstractHashCalculator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Testing extends Application {

	Label enterTextToHash;
	Label calculatedHash;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		enterTextToHash = new Label("Enter the text to hash:");
		enterTextToHash.setId("enterTextToHash");

		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);

		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		grid.add(enterTextToHash, 0, 0);

		addTextField(grid);
		addLabel(grid);
		addComboBox(grid);
		addButton(grid);

		Scene scene = new Scene(grid, 500, 500);
		stage.setScene(scene);

		stage.show();
	}

	public void addTextField(GridPane grid) {
		TextField textField = new TextField();
		textField.setId("textToHash");
		// root.getChildren().add(textField);
		grid.add(textField, 1, 0);
	}

	public void addLabel(GridPane grid) {
		Label selectHashingAlgorithm = new Label("Select Hashing Algorithm:");
		grid.add(selectHashingAlgorithm, 0, 1);
	}

	public void addComboBox(GridPane grid) {
		ObservableList<String> options = FXCollections.observableArrayList("MD5", "SHA256", "SHA512");
		final ComboBox<String> comboBox = new ComboBox<String>(options);
		comboBox.setId("hashCombobox");
		grid.add(comboBox, 1, 1);
	}

	public void addButton(GridPane grid) {

		calculatedHash = new Label();
		calculatedHash.setWrapText(true);
		Button hashCalculateButton = new Button("Calculate The Hash");
		hashCalculateButton.setAlignment(Pos.CENTER);
		hashCalculateButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg0) {
				try {
					ComboBox combobox = (ComboBox) grid.lookup("#hashCombobox");
					TextField textField = (TextField) grid.lookup("#textToHash");
					Class<?> clazz = Class.forName(getClassType(combobox.getValue().toString()));
					Object classObject = clazz.getDeclaredConstructor().newInstance();
					AbstractHashCalculator abstractHashCalculator = (AbstractHashCalculator) classObject;
					calculatedHash.setText(abstractHashCalculator.calculateHash(textField.getText()));
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		grid.add(hashCalculateButton, 0, 2);
		grid.add(calculatedHash, 0, 3);
	}

	private String getClassType(String comboboxValue) {
		String classType = AbstractHashCalculator.class.getPackageName() + "." + comboboxValue + "Hashing";
		return classType;
	}
}
