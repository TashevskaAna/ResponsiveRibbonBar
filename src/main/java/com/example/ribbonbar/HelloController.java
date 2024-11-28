package com.example.ribbonbar;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.input.MouseEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class HelloController {

    @FXML
    private TabPane ribbonTabPane;

    @FXML
    private ToolBar clipboardToolBar;

    @FXML
    private ToolBar fontToolBar;

    @FXML
    private Button alignLeftButton;
    @FXML
    private Button alignCenterButton;
    @FXML
    private Button alignRightButton;
    @FXML
    private Button justifyButton;
    @FXML
    private MenuButton numberingMenuButton;
    @FXML
    private ComboBox<String> fontFamilyComboBox;
    @FXML
    private ComboBox<String> fontSizeComboBox;
    @FXML
    private Button increaseFontSizeButton;
    @FXML
    private Button decreaseFontSizeButton;
    @FXML
    private Button boldButton;
    @FXML
    private Button italicButton;
    @FXML
    private MenuButton underlineButton;
    @FXML
    private Button subscriptButton;
    @FXML
    private Button superscriptButton;
    @FXML
    private MenuButton highlightButton;
    @FXML
    private MenuButton fontColorButton;
    @FXML
    private Button serviceButton1;

    @FXML
    private Button serviceButton;

    private Popup popup;
    @FXML
    private VBox popupContainer;

    @FXML
    private VBox fontSection;
    @FXML
    private Button fontToggleButton;
    @FXML
    private ToolBar fontToolBarRow2;
    @FXML
    private ToolBar fontToolBarRow1;
    @FXML
    private static final double FINAL_NARROW_THRESHOLD = 400.0;

    private Popup fontPopup;

    private static final double PARAGRAPH_NARROW_THRESHOLD1 = 650.0;
    @FXML
    private VBox paragraphSection;

    @FXML
    private Button paragraphToggleButton;

    @FXML
    private ToolBar paragToolBarRow1;

    @FXML
    private ToolBar paragToolBarRow2;

    @FXML
    private Button serviceButton2;


    private Popup paragraphPopup;

    @FXML
    private Button coverPageButton;

    private Popup templatePopup;


    @FXML
    public void initialize() {

            fontSection.setVisible(true);
            fontToolBarRow1.setVisible(true);
            fontToolBarRow2.setVisible(true);
            fontToggleButton.setVisible(false);

            fontPopup = createFontPopup();

        fontSection.sceneProperty().addListener((obs, oldScene, newScene) -> {
            if (newScene != null) {
                Stage stage = (Stage) fontSection.getScene().getWindow();
                stage.widthProperty().addListener((obsWidth, oldWidth, newWidth) -> updateFontSectionLayout(newWidth.doubleValue()));

                updateFontSectionLayout(stage.getWidth());
            }
        });


        fontToggleButton.setOnAction(e -> toggleFontPopup());


        if (paragraphSection != null && paragToolBarRow1 != null && paragToolBarRow2 != null && paragraphToggleButton != null) {
            paragraphSection.setVisible(true);
            paragToolBarRow1.setVisible(true);
            paragToolBarRow2.setVisible(true);
            paragraphToggleButton.setVisible(false);

            paragraphPopup = createParagraphPopup();

            paragraphSection.sceneProperty().addListener((obs, oldScene, newScene) -> {
                if (newScene != null) {
                    Stage stage = (Stage) paragraphSection.getScene().getWindow();
                    stage.widthProperty().addListener((obsWidth, oldWidth, newWidth) -> updateParagraphSectionLayout(newWidth.doubleValue()));
                    updateParagraphSectionLayout(stage.getWidth());
                }
            });

            // Toggle paragraph popup on click
            paragraphToggleButton.setOnAction(e -> toggleParagraphPopup());
        }

        setupPopup();

        popupContainer.setVisible(false);

        initializeFontFamilyComboBox();
        initializeFontSizeComboBox();

        ribbonTabPane.widthProperty().addListener((obs, oldWidth, newWidth) -> {
            double minWidth = 500; // Minimum width to show full controls
            if (newWidth.doubleValue() < minWidth) {
                hideButtonLabels();
            } else {
                restoreButtonLabels();
            }
        });

        alignLeftButton.setOnAction(e -> setAlignment("Left"));
        alignCenterButton.setOnAction(e -> setAlignment("Center"));
        alignRightButton.setOnAction(e -> setAlignment("Right"));
        justifyButton.setOnAction(e -> setAlignment("Justify"));

        initializeNumberingMenu();

        initializeFontActions();

        initializeColorActions();
    }

    private void updateParagraphSectionLayout(double width) {
        if (width < PARAGRAPH_NARROW_THRESHOLD1) {
            paragraphToggleButton.setVisible(true);
            paragToolBarRow1.setVisible(false);
            paragToolBarRow2.setVisible(false);
            serviceButton2.setVisible(false);
        } else {
            paragraphToggleButton.setVisible(false);
            paragToolBarRow1.setVisible(true);
            paragToolBarRow2.setVisible(true);
            serviceButton2.setVisible(true);
        }
    }

    private void toggleParagraphPopup() {
        if (paragraphPopup.isShowing()) {
            paragraphPopup.hide();
        } else {
            paragraphPopup.show(paragraphToggleButton,
                    paragraphToggleButton.getScene().getWindow().getX() + paragraphToggleButton.getLayoutX(),
                    paragraphToggleButton.getScene().getWindow().getY() + paragraphToggleButton.getLayoutY() + paragraphToggleButton.getHeight());
        }
    }

    private Popup createParagraphPopup() {
        Popup popup = new Popup();
        VBox popupContent = new VBox(5);
        popupContent.setStyle("-fx-padding: 10; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");

        ToolBar popupParagToolBarRow1 = new ToolBar(
                createMenuButton("●", "• Bullets", "○ Hollow Bullets", "■ Square Bullets"),
                createMenuButton("1.", "Numbered List", "Custom Numbering"),
                createMenuButton("1➔a", "1, a, i Multilevel List", "1.1, 1.1.1", "Custom Multilevel List")
        );

        ToolBar popupParagToolBarRow2 = new ToolBar(
                createButton("⬅", "Align Left"),
                createButton("⬌", "Center"),
                createButton("➡", "Align Right"),
                createButton("☰", "Justify"),
                createMenuButton("↕", "Single", "1.5 lines", "Double", "Multiple..."),
                createButton("A↔Z", "Sort"),
                createButton("¶", "Show/Hide Paragraph Marks"),
                createMenuButton("▢", "Bottom Border", "Top Border", "Left Border", "Right Border", "No Border", "All Borders", "Outside Borders", "Inside Borders")
        );

        Label popupParagLabel = new Label("Paragraph");
        popupParagLabel.setStyle("-fx-font-size: 10pt; -fx-text-fill: gray; -fx-padding: 5 0 0 0;");
        popupParagLabel.setAlignment(javafx.geometry.Pos.CENTER);

        HBox popupParagLabelContainer = new HBox(popupParagLabel);
        popupParagLabelContainer.setAlignment(javafx.geometry.Pos.CENTER);
        popupParagLabelContainer.setStyle("-fx-padding: 5 0 5 0;");

        Button popupServiceButton2 = new Button("->☰");
        popupServiceButton2.setOnAction(e -> {
            openFontDialog();
            System.out.println("Service Button clicked in popup");
        });
        popupServiceButton2.setTooltip(new Tooltip("Open Font Settings"));
        // Apply the CSS styling by setting the id
        popupServiceButton2.setId("serviceButton2");

        HBox popupServiceButtonContainer2 = new HBox(popupServiceButton2);
        popupServiceButtonContainer2.setStyle("-fx-alignment: center-right; -fx-padding: 5 10 5 10;");

        popupContent.getChildren().addAll(popupParagToolBarRow1, popupParagToolBarRow2, popupParagLabelContainer, popupServiceButtonContainer2);
        popup.getContent().add(popupContent);

        EventHandler<MouseEvent> clickOutsideHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                if (!popupContent.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                    popup.hide();
                    paragraphToggleButton.getScene().removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
            }
        };

        popup.setOnShowing(event -> {
            Scene scene = paragraphToggleButton.getScene();
            if (scene != null) {
                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, clickOutsideHandler);
            }
        });

        popup.setOnHidden(event -> {
            Scene scene = paragraphToggleButton.getScene();
            if (scene != null) {
                scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, clickOutsideHandler);
            }
        });

        return popup;
    }

    private Button createButton(String text, String tooltipText) {
        Button button = new Button(text);
        button.setTooltip(new Tooltip(tooltipText));
        button.setOnAction(e -> System.out.println("Action: " + tooltipText));
        return button;
    }


    private MenuButton createMenuButton(String text, String... menuItems) {
        MenuButton menuButton = new MenuButton(text);
        menuButton.setTooltip(new Tooltip(text));
        for (String item : menuItems) {
            MenuItem menuItem = new MenuItem(item);
            menuItem.setOnAction(e -> System.out.println("Selected: " + item));
            menuButton.getItems().add(menuItem);
        }
        return menuButton;
    }


    private void updateFontSectionLayout(double width) {
        if (width < FINAL_NARROW_THRESHOLD) {
            fontToggleButton.setVisible(true);
            fontToolBarRow1.setVisible(false);
            fontToolBarRow2.setVisible(false);
            serviceButton1.setVisible(false);
        } else {
            fontToggleButton.setVisible(false);
            fontToolBarRow1.setVisible(true);
            fontToolBarRow2.setVisible(true);
            serviceButton1.setVisible(true);
        }
    }

    private Popup createFontPopup() {
        Popup popup = new Popup();

        VBox popupContent = new VBox(5);
        popupContent.setStyle("-fx-padding: 10; -fx-background-color: #FFFFFF; -fx-border-color: #CCCCCC;");

        ComboBox<String> popupFontFamilyComboBox = new ComboBox<>(fontFamilyComboBox.getItems());
        popupFontFamilyComboBox.setPromptText(fontFamilyComboBox.getPromptText());
        popupFontFamilyComboBox.setOnAction(e -> {
            String selectedFont = popupFontFamilyComboBox.getValue();
            System.out.println("Font Family selected in popup: " + selectedFont);
        });

        ComboBox<String> popupFontSizeComboBox = new ComboBox<>(fontSizeComboBox.getItems());
        popupFontSizeComboBox.setPromptText(fontSizeComboBox.getPromptText());
        popupFontSizeComboBox.setOnAction(e -> {
            String selectedSize = popupFontSizeComboBox.getValue();
            System.out.println("Font Size selected in popup: " + selectedSize);
        });

        Button popupIncreaseFontSizeButton = new Button("A⁺");
        popupIncreaseFontSizeButton.setOnAction(e -> {
            System.out.println("Increase Font Size clicked in popup");
        });

        Button popupDecreaseFontSizeButton = new Button("A⁻");
        popupDecreaseFontSizeButton.setOnAction(e -> {
            System.out.println("Decrease Font Size clicked in popup");
        });

        Button popupBoldButton = new Button("B");
        popupBoldButton.setStyle("-fx-font-weight: bold;");
        popupBoldButton.setOnAction(e -> {
            System.out.println("Bold clicked in popup");
        });

        Button popupItalicButton = new Button("I");
        popupItalicButton.setStyle("-fx-font-style: italic;");
        popupItalicButton.setOnAction(e -> {
            System.out.println("Italic clicked in popup");
        });

        MenuButton popupUnderlineButton = new MenuButton("U");
        popupUnderlineButton.getItems().addAll(
                createUnderlineMenuItem("Single"),
                createUnderlineMenuItem("Double"),
                createUnderlineMenuItem("Dotted"),
                createUnderlineMenuItem("Dashed"),
                createUnderlineMenuItem("Wave")
        );

        Button popupSubscriptButton = new Button("x₂");
        popupSubscriptButton.setOnAction(e -> {
            System.out.println("Subscript clicked in popup");
        });

        Button popupSuperscriptButton = new Button("x²");
        popupSuperscriptButton.setOnAction(e -> {
            System.out.println("Superscript clicked in popup");
        });

        MenuButton popupHighlightButton = new MenuButton("A");
        popupHighlightButton.getItems().addAll(
                createHighlightColorMenuItem("Yellow"),
                createHighlightColorMenuItem("Green"),
                createHighlightColorMenuItem("Blue"),
                createHighlightColorMenuItem("Pink")
        );

        MenuButton popupFontColorButton = new MenuButton("A");
        popupFontColorButton.getItems().addAll(
                createFontColorMenuItem("Red"),
                createFontColorMenuItem("Blue"),
                createFontColorMenuItem("Green"),
                createFontColorMenuItem("Black")
        );

        Label popupFontLabel = new Label("Font");
        popupFontLabel.setStyle("-fx-font-size: 10pt; -fx-text-fill: gray; -fx-padding: 5 0 0 0;");
        popupFontLabel.setAlignment(javafx.geometry.Pos.CENTER);


        HBox popupFontLabelContainer = new HBox(popupFontLabel);
        popupFontLabelContainer.setAlignment(javafx.geometry.Pos.CENTER);
        popupFontLabelContainer.setStyle("-fx-padding: 5 0 5 0;");

        Button popupServiceButton = new Button("->☰");
        popupServiceButton.setOnAction(e -> {
            openFontDialog();
            System.out.println("Service Button clicked in popup");
        });
        popupServiceButton.setTooltip(new Tooltip("Open Font Settings"));
        popupServiceButton.setId("serviceButton1");

        HBox popupServiceButtonContainer = new HBox(popupServiceButton);
        popupServiceButtonContainer.setStyle("-fx-alignment: center-right; -fx-padding: 5 10 5 10;");

        ToolBar popupFontToolBarRow1 = new ToolBar(
                popupFontFamilyComboBox,
                popupFontSizeComboBox,
                popupIncreaseFontSizeButton,
                popupDecreaseFontSizeButton,
                popupBoldButton,
                popupItalicButton,
                popupUnderlineButton
        );

        ToolBar popupFontToolBarRow2 = new ToolBar(
                popupSubscriptButton,
                popupSuperscriptButton,
                popupHighlightButton,
                popupFontColorButton
        );

        popupContent.getChildren().addAll(popupFontToolBarRow1, popupFontToolBarRow2,popupFontLabelContainer, popupServiceButtonContainer);
        popup.getContent().add(popupContent);

        EventHandler<MouseEvent> clickOutsideHandler = new EventHandler<>() {
            @Override
            public void handle(MouseEvent e) {
                if (!popupContent.getBoundsInParent().contains(e.getSceneX(), e.getSceneY())) {
                    popup.hide();
                    fontToggleButton.getScene().removeEventFilter(MouseEvent.MOUSE_PRESSED, this);
                }
            }
        };

        popup.setOnShowing(event -> {
            Scene scene = fontToggleButton.getScene();
            if (scene != null) {
                scene.addEventFilter(MouseEvent.MOUSE_PRESSED, clickOutsideHandler);
            }
        });

        popup.setOnHidden(event -> {
            Scene scene = fontToggleButton.getScene();
            if (scene != null) {
                scene.removeEventFilter(MouseEvent.MOUSE_PRESSED, clickOutsideHandler);
            }
        });

        return popup;
    }

    private MenuItem createUnderlineMenuItem(String style) {
        MenuItem menuItem = new MenuItem(style);
        menuItem.setOnAction(e -> {
            System.out.println("Underline style selected in popup: " + style);
        });
        return menuItem;
    }

    private MenuItem createHighlightColorMenuItem(String color) {
        MenuItem menuItem = new MenuItem(color);
        menuItem.setOnAction(e -> {
            System.out.println("Highlight color selected in popup: " + color);
        });
        return menuItem;
    }

    private MenuItem createFontColorMenuItem(String color) {
        MenuItem menuItem = new MenuItem(color);
        menuItem.setOnAction(e -> {
            System.out.println("Font color selected in popup: " + color);
        });
        return menuItem;
    }


    private void toggleFontPopup() {
        if (fontPopup.isShowing()) {
            fontPopup.hide();
        } else {
            if (fontToggleButton.isVisible()) {
                fontPopup.show(fontToggleButton, fontToggleButton.getScene().getWindow().getX() + fontToggleButton.getLayoutX(),
                        fontToggleButton.getScene().getWindow().getY() + fontToggleButton.getLayoutY() + fontToggleButton.getHeight());
            }
        }
    }

    private void setupPopup() {
        popup = new Popup();

    }



    @FXML
    private void showPopup() {
        if (!popup.isShowing()) {
            popup.show(serviceButton, serviceButton.getLayoutX() - 200, serviceButton.getLayoutY() + serviceButton.getHeight());
        } else {
            popup.hide();
        }
        popupContainer.setVisible(!popupContainer.isVisible());
    }


    @FXML
    private void showCoverPageTemplates() {
        if (templatePopup == null) {
            templatePopup = new Popup();
            templatePopup.setAutoHide(true);

            ScrollPane scrollPane = new ScrollPane();
            scrollPane.setPrefSize(400, 300);
            scrollPane.setFitToWidth(true);

            VBox templateContainer = new VBox(10);
            templateContainer.setPadding(new Insets(10));
            templateContainer.setStyle("-fx-background-color: white;");

            for (int i = 1; i <= 3; i++) {
                final int templateNumber = i;
                HBox template = new HBox(10);
                template.setAlignment(Pos.CENTER_LEFT);
                template.setPadding(new Insets(5));
                template.setStyle("-fx-border-color: lightgray; -fx-border-width: 1;");
                ImageView templateImage = new ImageView();
                try {
                    switch (templateNumber) {
                        case 1:
                            templateImage.setImage(new Image(getClass().getResource("/com/example/ribbonbar/images/template1.png").toExternalForm()));
                            break;
                        case 2:
                            templateImage.setImage(new Image(getClass().getResource("/com/example/ribbonbar/images/template2.png").toExternalForm()));
                            break;
                        case 3:
                            templateImage.setImage(new Image(getClass().getResource("/com/example/ribbonbar/images/template3.png").toExternalForm()));
                            break;
                    }
                    templateImage.setFitHeight(100.0);
                    templateImage.setFitWidth(75.0);
                } catch (NullPointerException e) {
                    System.out.println("Template image not found for: template" + templateNumber + ".png");
                }

                Label templateLabel = new Label("Template " + i);
                templateLabel.setStyle("-fx-font-size: 12px;");

                template.getChildren().addAll(templateImage, templateLabel);

                template.setOnMouseClicked(event -> {
                    System.out.println("Selected Template: Template " + templateNumber);
                    templatePopup.hide();
                });

                templateContainer.getChildren().add(template);
            }

            scrollPane.setContent(templateContainer);

            VBox popupContent = new VBox(10);
            popupContent.setStyle("-fx-background-color: white; -fx-padding: 10; -fx-border-color: gray; -fx-border-width: 1;");
            popupContent.getChildren().addAll(new Label("Select a Cover Page"), scrollPane);

            templatePopup.getContent().add(popupContent);
        }


        if (!templatePopup.isShowing()) {
            templatePopup.show(
                    coverPageButton,
                    coverPageButton.getScene().getWindow().getX() + coverPageButton.localToScene(coverPageButton.getBoundsInLocal()).getMinX(),
                    coverPageButton.getScene().getWindow().getY() + coverPageButton.localToScene(coverPageButton.getBoundsInLocal()).getMinY() + coverPageButton.getHeight()
            );
        }
    }



    @FXML
    public void openFontDialog() {
        Stage fontDialog = new Stage();
        fontDialog.initModality(Modality.APPLICATION_MODAL);
        fontDialog.setTitle("Font");

        VBox dialogLayout = new VBox(10);
        dialogLayout.setStyle("-fx-padding: 10;");

        HBox fontOptions = new HBox(10);
        ListView<String> fontList = new ListView<>();
        fontList.getItems().addAll("Calibri", "Arial", "Times New Roman", "Verdana");
        fontList.setPrefSize(100, 100);

        ListView<String> fontStyleList = new ListView<>();
        fontStyleList.getItems().addAll("Regular", "Italic", "Bold", "Bold Italic");
        fontStyleList.setPrefSize(100, 100);

        ListView<String> fontSizeList = new ListView<>();
        fontSizeList.getItems().addAll("8", "9", "10", "11", "12", "14", "16", "18", "20");
        fontSizeList.setPrefSize(50, 100);

        fontOptions.getChildren().addAll(fontList, fontStyleList, fontSizeList);

        HBox colorOptions = new HBox(10);
        ComboBox<String> fontColor = new ComboBox<>();
        fontColor.getItems().addAll("Automatic", "Red", "Green", "Blue");

        ComboBox<String> underlineStyle = new ComboBox<>();
        underlineStyle.getItems().addAll("(none)", "Single", "Double");

        ComboBox<String> underlineColor = new ComboBox<>();
        underlineColor.getItems().addAll("Automatic", "Red", "Green", "Blue");

        colorOptions.getChildren().addAll(new Label("Font color:"), fontColor, new Label("Underline style:"), underlineStyle, new Label("Underline color:"), underlineColor);

        GridPane effects = new GridPane();
        effects.setHgap(10);
        effects.setVgap(5);

        CheckBox strikethrough = new CheckBox("Strikethrough");
        CheckBox doubleStrikethrough = new CheckBox("Double Strikethrough");
        CheckBox superscript = new CheckBox("Superscript");
        CheckBox subscript = new CheckBox("Subscript");
        CheckBox shadow = new CheckBox("Shadow");
        CheckBox outline = new CheckBox("Outline");
        CheckBox emboss = new CheckBox("Emboss");
        CheckBox engrave = new CheckBox("Engrave");
        CheckBox smallCaps = new CheckBox("Small Caps");
        CheckBox allCaps = new CheckBox("All Caps");
        CheckBox hidden = new CheckBox("Hidden");

        effects.add(strikethrough, 0, 0);
        effects.add(doubleStrikethrough, 1, 0);
        effects.add(superscript, 2, 0);
        effects.add(subscript, 3, 0);
        effects.add(shadow, 0, 1);
        effects.add(outline, 1, 1);
        effects.add(emboss, 2, 1);
        effects.add(engrave, 3, 1);
        effects.add(smallCaps, 0, 2);
        effects.add(allCaps, 1, 2);
        effects.add(hidden, 2, 2);

        Label previewLabel = new Label("Preview");
        Label previewText = new Label("Calibri");
        previewText.setStyle("-fx-font-size: 12pt;");

        HBox preview = new HBox(10, previewLabel, previewText);

        HBox dialogButtons = new HBox(10);
        Button setDefault = new Button("Set As Default");
        Button textEffects = new Button("Text Effects...");
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        dialogButtons.getChildren().addAll(setDefault, textEffects, okButton, cancelButton);

        dialogLayout.getChildren().addAll(fontOptions, colorOptions, effects, preview, dialogButtons);

        Scene dialogScene = new Scene(dialogLayout, 450, 400);
        fontDialog.setScene(dialogScene);
        fontDialog.showAndWait();
    }

    private void hideButtonLabels() {
        if (clipboardToolBar != null) {
            clipboardToolBar.getItems().forEach(item -> {
                if (item instanceof Button) {
                    ((Button) item).setText("");
                }
            });
        }
        if (fontToolBar != null) {
            fontToolBar.getItems().forEach(item -> {
                if (item instanceof Button) {
                    ((Button) item).setText("");
                }
            });
        }
    }

    private void restoreButtonLabels() {
        if (clipboardToolBar != null && clipboardToolBar.getItems().size() >= 3) {
            ((Button) clipboardToolBar.getItems().get(0)).setText("Cut");
            ((Button) clipboardToolBar.getItems().get(1)).setText("Copy");
            ((Button) clipboardToolBar.getItems().get(2)).setText("Paste");
        }
        if (fontToolBar != null && fontToolBar.getItems().size() >= 3) {
            ((Button) fontToolBar.getItems().get(0)).setText("Bold");
            ((Button) fontToolBar.getItems().get(1)).setText("Italic");
            ((Button) fontToolBar.getItems().get(2)).setText("Underline");
        }
    }

    private void initializeFontFamilyComboBox() {
        fontFamilyComboBox.getItems().addAll("Arial", "Calibri", "Times New Roman", "Verdana", "Courier New");
        fontFamilyComboBox.setOnAction(e -> System.out.println("Selected font family: " + fontFamilyComboBox.getValue()));
    }

    private void initializeFontSizeComboBox() {
        fontSizeComboBox.getItems().addAll("8", "10", "12", "14", "16", "18", "20", "24", "28", "32", "36", "48", "72");
        fontSizeComboBox.setOnAction(e -> System.out.println("Selected font size: " + fontSizeComboBox.getValue()));
    }

    private void setAlignment(String alignment) {
        System.out.println("Text aligned to: " + alignment);
    }

    private void initializeNumberingMenu() {
        MenuItem numberedList = new MenuItem("1. Numbered List");
        MenuItem letteredList = new MenuItem("A. Lettered List");
        MenuItem romanNumerals = new MenuItem("I. Roman Numerals");

        numberedList.setOnAction(e -> setNumbering("Numbered List"));
        letteredList.setOnAction(e -> setNumbering("Lettered List"));
        romanNumerals.setOnAction(e -> setNumbering("Roman Numerals"));

        numberingMenuButton.getItems().addAll(numberedList, letteredList, romanNumerals);
    }
    @FXML
    public void openParagraphDialog() {
        Stage paragraphDialog = new Stage();
        paragraphDialog.initModality(Modality.APPLICATION_MODAL);
        paragraphDialog.setTitle("Paragraph");

        VBox dialogLayout = new VBox(10);
        dialogLayout.setStyle("-fx-padding: 15;");

        HBox generalOptions = new HBox(10);
        ComboBox<String> alignmentBox = new ComboBox<>();
        alignmentBox.getItems().addAll("Left", "Center", "Right", "Justified");
        alignmentBox.setValue("Left");

        ComboBox<String> outlineLevelBox = new ComboBox<>();
        outlineLevelBox.getItems().addAll("Body Text", "Level 1", "Level 2", "Level 3");
        outlineLevelBox.setValue("Body Text");

        CheckBox collapsedByDefault = new CheckBox("Collapsed by default");

        generalOptions.getChildren().addAll(new Label("Alignment:"), alignmentBox, new Label("Outline level:"), outlineLevelBox, collapsedByDefault);

        GridPane indentationOptions = new GridPane();
        indentationOptions.setHgap(10);
        indentationOptions.setVgap(5);

        Spinner<Double> leftIndent = new Spinner<>(0.0, 10.0, 0.0, 0.5);
        Spinner<Double> rightIndent = new Spinner<>(0.0, 10.0, 0.0, 0.5);
        ComboBox<String> specialIndent = new ComboBox<>();
        specialIndent.getItems().addAll("(none)", "First line", "Hanging");
        Spinner<Double> specialBy = new Spinner<>(0.0, 5.0, 0.0, 0.5);
        CheckBox mirrorIndents = new CheckBox("Mirror indents");

        indentationOptions.add(new Label("Left:"), 0, 0);
        indentationOptions.add(leftIndent, 1, 0);
        indentationOptions.add(new Label("Right:"), 2, 0);
        indentationOptions.add(rightIndent, 3, 0);
        indentationOptions.add(new Label("Special:"), 0, 1);
        indentationOptions.add(specialIndent, 1, 1);
        indentationOptions.add(new Label("By:"), 2, 1);
        indentationOptions.add(specialBy, 3, 1);
        indentationOptions.add(mirrorIndents, 0, 2, 4, 1);

        GridPane spacingOptions = new GridPane();
        spacingOptions.setHgap(10);
        spacingOptions.setVgap(5);

        Spinner<Double> beforeSpacing = new Spinner<>(0.0, 50.0, 0.0, 1.0);
        Spinner<Double> afterSpacing = new Spinner<>(0.0, 50.0, 8.0, 1.0);
        ComboBox<String> lineSpacing = new ComboBox<>();
        lineSpacing.getItems().addAll("Single", "1.5 lines", "Double", "Multiple");
        lineSpacing.setValue("Multiple");
        Spinner<Double> atSpacing = new Spinner<>(1.0, 3.0, 1.08, 0.01);
        CheckBox noSpaceBetweenParagraphs = new CheckBox("Don't add space between paragraphs of the same style");

        spacingOptions.add(new Label("Before:"), 0, 0);
        spacingOptions.add(beforeSpacing, 1, 0);
        spacingOptions.add(new Label("After:"), 2, 0);
        spacingOptions.add(afterSpacing, 3, 0);
        spacingOptions.add(new Label("Line spacing:"), 0, 1);
        spacingOptions.add(lineSpacing, 1, 1);
        spacingOptions.add(new Label("At:"), 2, 1);
        spacingOptions.add(atSpacing, 3, 1);
        spacingOptions.add(noSpaceBetweenParagraphs, 0, 2, 4, 1);

        Label previewLabel = new Label("Preview");
        Label previewText = new Label("Sample Text Sample Text Sample Text Sample Text");
        previewText.setStyle("-fx-border-color: gray; -fx-padding: 10;");

        HBox dialogButtons = new HBox(10);
        Button tabsButton = new Button("Tabs...");
        Button setDefaultButton = new Button("Set As Default");
        Button okButton = new Button("OK");
        Button cancelButton = new Button("Cancel");

        dialogButtons.getChildren().addAll(tabsButton, setDefaultButton, okButton, cancelButton);

        dialogLayout.getChildren().addAll(generalOptions, new Separator(), indentationOptions, new Separator(), spacingOptions, new Separator(), previewLabel, previewText, dialogButtons);

        Scene dialogScene = new Scene(dialogLayout, 500, 500);
        paragraphDialog.setScene(dialogScene);
        paragraphDialog.showAndWait();
    }
    private void setNumbering(String numberingStyle) {
        System.out.println("Numbering style set to: " + numberingStyle);
    }

    private void initializeFontActions() {
        boldButton.setOnAction(e -> toggleStyle("Bold"));
        italicButton.setOnAction(e -> toggleStyle("Italic"));
        underlineButton.getItems().forEach(item -> item.setOnAction(e -> setUnderlineStyle(item.getText())));

        increaseFontSizeButton.setOnAction(e -> adjustFontSize(1));
        decreaseFontSizeButton.setOnAction(e -> adjustFontSize(-1));

        subscriptButton.setOnAction(e -> toggleStyle("Subscript"));
        superscriptButton.setOnAction(e -> toggleStyle("Superscript"));
    }

    private void toggleStyle(String style) {
        System.out.println(style + " toggled.");
    }

    private void setUnderlineStyle(String style) {
        System.out.println("Underline style set to: " + style);
    }

    private void adjustFontSize(int delta) {
        System.out.println("Font size adjusted by: " + delta);
    }

    private void initializeColorActions() {
        highlightButton.getItems().forEach(item -> item.setOnAction(e -> {
            String color = item.getText();
            setHighlightColor(color);
            highlightButton.setStyle("-fx-background-color: " + color.toLowerCase() + ";");
        }));

        fontColorButton.getItems().forEach(item -> item.setOnAction(e -> {
            String color = item.getText();
            setFontColor(color);
            fontColorButton.setStyle("-fx-text-fill: " + color.toLowerCase() + ";");
        }));
    }

    private void setHighlightColor(String color) {
        System.out.println("Highlight color set to: " + color);
    }

    private void setFontColor(String color) {
        System.out.println("Font color set to: " + color);
    }
}
