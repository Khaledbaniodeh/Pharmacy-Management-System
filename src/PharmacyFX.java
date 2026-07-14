import javafx.scene.layout.*;
import javafx.application.Platform;
import javafx.application.Application;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;

import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class PharmacyFX extends Application {

    private BorderPane root;
    private Stage stage;

    private final String BG = "-fx-background-color: #f4f6f8;";
    private final String SIDEBAR = "-fx-background-color: #111827;";
    private final String CARD =
            "-fx-background-color: white;" +
            "-fx-background-radius: 14;" +
            "-fx-padding: 18;" +
            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.12), 10, 0, 0, 3);";

    private final String TITLE =
            "-fx-font-size: 26px;" +
            "-fx-font-weight: bold;" +
            "-fx-text-fill: #111827;";

    private final String SUBTITLE =
            "-fx-font-size: 14px;" +
            "-fx-text-fill: #6b7280;";

    private final String BTN =
            "-fx-background-color: #2563eb;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 9 15;" +
            "-fx-cursor: hand;";

    private final String GREEN =
            "-fx-background-color: #059669;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 9 15;" +
            "-fx-cursor: hand;";

    private final String RED =
            "-fx-background-color: #dc2626;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 9 15;" +
            "-fx-cursor: hand;";

    private final String[] CHART_COLORS = {
            "#2563eb", "#059669", "#f59e0b", "#dc2626",
            "#7c3aed", "#0891b2", "#ea580c", "#65a30d",
            "#0f766e", "#be123c", "#4f46e5", "#84cc16"
    };

    private final String GRAY =
            "-fx-background-color: #6b7280;" +
            "-fx-text-fill: white;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 8;" +
            "-fx-padding: 9 15;" +
            "-fx-cursor: hand;";

    @Override
    public void start(Stage primaryStage) {
        this.stage = primaryStage;
        showLogin();
        primaryStage.setMaximized(true);
    }

    private void showLogin() {
        BorderPane page = new BorderPane();
        page.setStyle("-fx-background-color: linear-gradient(to bottom right, #0f172a, #2563eb);");

        HBox loginShell = new HBox();
        loginShell.setMaxWidth(1050);
        loginShell.setPrefHeight(620);
        loginShell.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 24;" +
                "-fx-effect: dropshadow(gaussian, rgba(15,23,42,0.28), 25, 0, 0, 8);"
        );

        StackPane imagePane = new StackPane();
        imagePane.setPrefWidth(520);
        imagePane.setMinWidth(520);
        imagePane.setStyle("-fx-background-color: #dbeafe; -fx-background-radius: 24 0 0 24;");

        ImageView loginImage = createLoginImageView();

        Region overlay = new Region();
        overlay.setStyle(
                "-fx-background-color: linear-gradient(to top, rgba(15,23,42,0.78), rgba(15,23,42,0.08));" +
                "-fx-background-radius: 24 0 0 24;"
        );
        overlay.prefWidthProperty().bind(imagePane.widthProperty());
        overlay.prefHeightProperty().bind(imagePane.heightProperty());

        VBox imageText = new VBox(8);
        imageText.setPadding(new Insets(0, 28, 30, 28));
        imageText.setAlignment(Pos.BOTTOM_LEFT);

        Label imageTitle = new Label("Noor AlHuda Pharmacy");
        imageTitle.setStyle("-fx-font-size: 26px; -fx-font-weight: bold; -fx-text-fill: white;");

        Label imageSub = new Label("Smart Pharmacy Management System");
        imageSub.setWrapText(true);
        imageSub.setStyle("-fx-font-size: 14px; -fx-text-fill: rgba(255,255,255,0.95);");

        imageText.getChildren().addAll(imageTitle, imageSub);
        imagePane.getChildren().addAll(loginImage, overlay, imageText);
        StackPane.setAlignment(imageText, Pos.BOTTOM_LEFT);

        VBox formPane = new VBox(14);
        formPane.setAlignment(Pos.CENTER_LEFT);
        formPane.setPadding(new Insets(45));
        formPane.setPrefWidth(530);
        formPane.setStyle("-fx-background-color: white; -fx-background-radius: 0 24 24 0;");

        Label badge = new Label("MANAGER ACCESS");
        badge.setStyle(
                "-fx-background-color: #dbeafe;" +
                "-fx-text-fill: #1d4ed8;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 6 12;" +
                "-fx-background-radius: 30;"
        );

        Label title = new Label("Pharmacy Sales Management System");
        title.setWrapText(true);
        title.setStyle("-fx-font-size: 28px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label sub = new Label("Sign in to continue to the dashboard");
        sub.setStyle(SUBTITLE);

        TextField user = new TextField();
        user.setPromptText("Username");
        user.setMaxWidth(Double.MAX_VALUE);
        user.setPrefHeight(42);

        PasswordField pass = new PasswordField();
        pass.setPromptText("Password");
        pass.setMaxWidth(Double.MAX_VALUE);
        pass.setPrefHeight(42);

        Button login = new Button("Login");
        login.setStyle(BTN + "-fx-font-size: 15px; -fx-padding: 12 18;");
        login.setMaxWidth(Double.MAX_VALUE);
        login.setPrefHeight(44);

        Label db = new Label(testConnection() ? "Database Connected" : "Database Not Connected");
        db.setStyle(testConnection()
                ? "-fx-text-fill: #059669; -fx-font-weight: bold;"
                : "-fx-text-fill: #dc2626; -fx-font-weight: bold;");

        Label hint = new Label("Demo login: admin / admin");
        hint.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");

        login.setOnAction(e -> {
            if (user.getText().trim().equals("admin") && pass.getText().trim().equals("admin")) {
                showApp();
            } else {
                alert(Alert.AlertType.ERROR, "Login Failed", "Use admin / admin");
            }
        });

        formPane.getChildren().addAll(badge, title, sub, user, pass, login, db, hint);
        HBox.setHgrow(formPane, Priority.ALWAYS);

        loginShell.getChildren().addAll(imagePane, formPane);

        StackPane center = new StackPane(loginShell);
        center.setPadding(new Insets(24));
        page.setCenter(center);

        stage.setScene(new Scene(page, 1200, 760));
        stage.setTitle("Pharmacy Login");
        stage.show();
        keepWindowMaximized();
    }

    private void showApp() {
        root = new BorderPane();
        root.setStyle(BG);
        root.setLeft(sidebar());
        showDashboard();

        stage.setScene(new Scene(root, 1280, 780));
        stage.setTitle("Pharmacy Sales Management System");
        stage.show();
        keepWindowMaximized();
    }

    private void keepWindowMaximized() {
        stage.setResizable(true);
        stage.setMaximized(true);
        Platform.runLater(() -> {
            stage.setMaximized(true);
            stage.toFront();
        });
    }

    private VBox sidebar() {
        VBox side = new VBox(9);
        side.setPadding(new Insets(25, 14, 25, 14));
        side.setPrefWidth(240);
        side.setStyle(SIDEBAR);

        Label logo = new Label("Pharmacy\nSystem");
        logo.setStyle("-fx-font-size: 23px; -fx-font-weight: bold; -fx-text-fill: white;");

        Button dashboard = sideButton("Dashboard");
        Button medicines = sideButton("Medicines");
        Button inventory = sideButton("Inventory / Stock");
        Button customers = sideButton("Customers");
        Button suppliers = sideButton("Suppliers");
        Button warehouses = sideButton("Warehouses");
        Button sales = sideButton("Sales");
        Button purchases = sideButton("Purchases");
        Button reports = sideButton("Reports / Statistics");
        Button logout = sideButton("Logout");

        dashboard.setOnAction(e -> showDashboard());
        medicines.setOnAction(e -> showMedicines());
        inventory.setOnAction(e -> showInventory());
        customers.setOnAction(e -> showCustomers());
        suppliers.setOnAction(e -> showSuppliers());
        warehouses.setOnAction(e -> showWarehouses());
        sales.setOnAction(e -> showSales());
        purchases.setOnAction(e -> showPurchases());
        reports.setOnAction(e -> showReports());
        logout.setOnAction(e -> showLogin());

        side.getChildren().addAll(
                logo,
                gap(15),
                dashboard,
                medicines,
                inventory,
                customers,
                suppliers,
                warehouses,
                sales,
                purchases,
                reports,
                gap(20),
                logout
        );

        return side;
    }

    private Button sideButton(String text) {
        Button b = new Button(text);
        b.setMaxWidth(Double.MAX_VALUE);
        b.setAlignment(Pos.CENTER_LEFT);
        b.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #d1d5db;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 14;" +
                "-fx-cursor: hand;"
        );

        b.setOnMouseEntered(e -> b.setStyle(
                "-fx-background-color: #374151;" +
                "-fx-background-radius: 8;" +
                "-fx-text-fill: white;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 14;" +
                "-fx-cursor: hand;"
        ));

        b.setOnMouseExited(e -> b.setStyle(
                "-fx-background-color: transparent;" +
                "-fx-text-fill: #d1d5db;" +
                "-fx-font-size: 14px;" +
                "-fx-padding: 10 14;" +
                "-fx-cursor: hand;"
        ));

        return b;
    }

    private Label gap(int h) {
        Label l = new Label();
        l.setMinHeight(h);
        return l;
    }

    private void setCenter(VBox page) {
        ScrollPane sp = new ScrollPane(page);
        sp.setFitToWidth(true);
        sp.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        root.setCenter(sp);
    }

    private void setCenterWithFixedRight(VBox content, Node rightPanel) {
        BorderPane area = new BorderPane();
        area.setStyle(BG);

        ScrollPane contentScroll = new ScrollPane(content);
        contentScroll.setFitToWidth(true);
        contentScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        area.setCenter(contentScroll);

        ScrollPane rightScroll = new ScrollPane(rightPanel);
        rightScroll.setFitToWidth(true);
        rightScroll.setPrefWidth(470);
        rightScroll.setMinWidth(450);
        rightScroll.setMaxWidth(500);
        rightScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        BorderPane.setMargin(rightScroll, new Insets(26, 26, 26, 12));
        area.setRight(rightScroll);

        root.setCenter(area);
    }

    private VBox crudSidePanel(String title, Node form, Node actions) {
        VBox panel = new VBox(14);
        panel.setPrefWidth(450);
        panel.setMinWidth(430);
        panel.setMaxWidth(480);
        panel.setPadding(new Insets(18));
        panel.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 16;" +
                "-fx-effect: dropshadow(gaussian, rgba(15,23,42,0.16), 14, 0, 0, 4);"
        );

        Label t = new Label(title);
        t.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label hint = new Label("Select a card from the left, then use this fixed panel to add, update, delete, or clear.");
        hint.setWrapText(true);
        hint.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        panel.getChildren().addAll(t, hint, form, actions);
        return panel;
    }

    private void showDashboard() {
        VBox page = new VBox(20);
        page.setPadding(new Insets(26));

        Label title = new Label("Manager Dashboard");
        title.setStyle(TITLE);

        Label sub = new Label("Overview of pharmacy sales, medicines, customers, and stock.");
        sub.setStyle(SUBTITLE);

        GridPane cards = new GridPane();
        cards.setHgap(16);
        cards.setVgap(16);

        cards.add(summaryCard("Total Medicines", one("SELECT COUNT(*) FROM medicines")), 0, 0);
        cards.add(summaryCard("Total Customers", one("SELECT COUNT(*) FROM customers")), 1, 0);
        cards.add(summaryCard("Total Sales", one("SELECT COUNT(*) FROM sales")), 2, 0);
        cards.add(summaryCard("Low Stock", one("SELECT COUNT(*) FROM inventory WHERE quantity <= reorder_level")), 3, 0);

        VBox logoCard = new VBox(12);
        logoCard.setStyle(CARD);
        logoCard.setAlignment(Pos.CENTER);

        Label logoTitle = new Label("Noor AlHuda Pharmacy");
        logoTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #0f172a;");

        Label logoSub = new Label("Smart Pharmacy Management System");
        logoSub.setStyle(SUBTITLE);

        ImageView logoImageView = createLogoImageView();

        Label modulesTitle = new Label("System Main Modules");
        modulesTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #0f172a;");

        Label modulesSub = new Label("Main functional sections included in Noor AlHuda Pharmacy System");
        modulesSub.setStyle(SUBTITLE);

        ImageView modulesImageView = createModulesImageView();
        VBox.setMargin(modulesTitle, new Insets(24, 0, 0, 0));

        logoCard.getChildren().addAll(
                logoTitle,
                logoSub,
                logoImageView,
                modulesTitle,
                modulesSub,
                modulesImageView
        );

        page.getChildren().addAll(title, sub, cards, logoCard);
        setCenter(page);
    }

    private ImageView createLogoImageView() {
        InputStream stream = getClass().getResourceAsStream("/logo.png");

        if (stream == null) {
            alert(Alert.AlertType.ERROR, "Image Not Found",
                    "logo.png not found. Put logo.png inside the src folder (next to PharmacyFX.java).");
            return new ImageView();
        }

        Image img = new Image(stream);
        ImageView imgView = new ImageView(img);

        imgView.setFitWidth(430);
        imgView.setFitHeight(300);
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);

        return imgView;
    }

    private ImageView createModulesImageView() {
        InputStream stream = getClass().getResourceAsStream("/logo2.png");

        if (stream == null) {
            alert(Alert.AlertType.ERROR, "Image Not Found",
                    "logo2.png not found. Put logo2.png inside the src folder (next to PharmacyFX.java).");
            return new ImageView();
        }

        Image img = new Image(stream);
        ImageView imgView = new ImageView(img);

        imgView.setFitWidth(850);
        imgView.setFitHeight(520);
        imgView.setPreserveRatio(true);
        imgView.setSmooth(true);

        return imgView;
    }

    private ImageView createLoginImageView() {
        InputStream stream = getClass().getResourceAsStream("/logo1.png");

        if (stream == null) {
            alert(Alert.AlertType.ERROR, "Image Not Found",
                    "logo1.png not found. Put logo1.png inside the src folder.");
            return new ImageView();
        }

        Image img = new Image(stream);
        ImageView imgView = new ImageView(img);
        imgView.setFitWidth(520);
        imgView.setFitHeight(620);
        imgView.setPreserveRatio(false);
        imgView.setSmooth(true);
        return imgView;
    }

    private void loadReferenceOptions(ComboBox<String> combo, String sql) {
        combo.getItems().clear();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                combo.getItems().add(rs.getInt(1) + " - " + rs.getString(2));
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Load Failed", "Could not load dropdown values.\n" + friendlyDbMessage(e));
        }
    }

    private int selectedOptionId(ComboBox<String> combo, String fieldName) {
        String value = combo.getValue();
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(fieldName + " is required. Please choose it from the list.");
        }

        try {
            return Integer.parseInt(value.split(" - ")[0].trim());
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid " + fieldName + ". Please choose it from the list.");
        }
    }

    private void selectOptionById(ComboBox<String> combo, String id) {
        if (id == null || id.trim().isEmpty()) {
            combo.getSelectionModel().clearSelection();
            return;
        }

        for (String item : combo.getItems()) {
            if (item.startsWith(id.trim() + " - ")) {
                combo.setValue(item);
                return;
            }
        }

        combo.getSelectionModel().clearSelection();
    }

    private VBox summaryCard(String title, String value) {
        VBox c = new VBox(8);
        c.setPrefWidth(220);
        c.setMinHeight(110);
        c.setAlignment(Pos.CENTER_LEFT);
        c.setStyle(CARD);

        Label t = new Label(title);
        t.setStyle("-fx-font-size: 14px; -fx-text-fill: #6b7280;");

        Label v = new Label(value);
        v.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: #2563eb;");

        c.getChildren().addAll(t, v);
        return c;
    }

    // =========================================================
    // MEDICINES PAGE
    // =========================================================
    private void showMedicines() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Medicines Management");
        title.setStyle(TITLE);

        Label sub = new Label("Search, add, update, and delete/deactivate medicines. Medicines are displayed as clean cards instead of a table.");
        sub.setStyle(SUBTITLE);

        TextField search = new TextField();
        search.setPromptText("Filter Search: medicine / category / supplier / status");
        search.setPrefWidth(420);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Active Medicines", one("SELECT COUNT(*) FROM medicines WHERE status='ACTIVE'")),
                summaryCard("Inactive Medicines", one("SELECT COUNT(*) FROM medicines WHERE status='INACTIVE'")),
                summaryCard("Expired Medicines", one("SELECT COUNT(*) FROM medicines WHERE expiry_date < CURDATE()")),
                summaryCard("Low Stock Items", one("SELECT COUNT(*) FROM inventory WHERE quantity <= reorder_level"))
        );

        FlowPane medicineCards = new FlowPane(14, 14);
        medicineCards.setPadding(new Insets(5, 0, 5, 0));
        medicineCards.setPrefWrapLength(700);

        TextField name = new TextField();
        name.setPromptText("Medicine name");

        TextField desc = new TextField();
        desc.setPromptText("Description");

        TextField price = new TextField();
        price.setPromptText("Price");

        DatePicker expiry = new DatePicker();
        expiry.setPromptText("Expiry date");

        ComboBox<String> categoryBox = new ComboBox<>();
        categoryBox.setPromptText("Choose category");
        categoryBox.setPrefWidth(220);

        ComboBox<String> supplierBox = new ComboBox<>();
        supplierBox.setPromptText("Choose supplier");
        supplierBox.setPrefWidth(220);

        loadReferenceOptions(categoryBox,
                "SELECT category_id, category_name FROM categories ORDER BY category_name");
        loadReferenceOptions(supplierBox,
                "SELECT supplier_id, supplier_name FROM suppliers ORDER BY supplier_name");

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll("ACTIVE", "INACTIVE");
        status.setValue("ACTIVE");
        status.setPrefWidth(180);

        GridPane form = formGrid();
        form.add(new Label("Name"), 0, 0);
        form.add(name, 1, 0);
        form.add(new Label("Description"), 0, 1);
        form.add(desc, 1, 1);
        form.add(new Label("Price"), 0, 2);
        form.add(price, 1, 2);
        form.add(new Label("Expiry Date"), 0, 3);
        form.add(expiry, 1, 3);
        form.add(new Label("Category"), 0, 4);
        form.add(categoryBox, 1, 4);
        form.add(new Label("Supplier"), 0, 5);
        form.add(supplierBox, 1, 5);
        form.add(new Label("Status"), 0, 6);
        form.add(status, 1, 6);

        Button add = new Button("Add");
        add.setStyle(GREEN);

        Button update = new Button("Update Selected");
        update.setStyle(BTN);

        Button delete = new Button("Delete / Deactivate Selected");
        delete.setStyle(RED);

        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedMedicineId = {-1};
        final VBox[] selectedMedicineCard = {null};

        String selectSql =
                "SELECT m.medicine_id AS ID, m.medicine_name AS Medicine, m.description AS Description, " +
                "m.category_id AS Category_ID, c.category_name AS Category, " +
                "m.supplier_id AS Supplier_ID, s.supplier_name AS Supplier, " +
                "m.selling_price AS Price, m.expiry_date AS Expiry_Date, m.status AS Status " +
                "FROM medicines m " +
                "JOIN categories c ON m.category_id = c.category_id " +
                "JOIN suppliers s ON m.supplier_id = s.supplier_id " +
                "WHERE m.status = 'ACTIVE' ";

        Runnable refresh = () -> loadMedicineCards(
                medicineCards,
                selectSql + "ORDER BY m.medicine_name",
                selectedMedicineId,
                selectedMedicineCard,
                name, desc, price, expiry, categoryBox, supplierBox, status
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadMedicineCards(
                    medicineCards,
                    selectSql +
                            "AND (m.medicine_name LIKE ? OR m.description LIKE ? OR c.category_name LIKE ? " +
                            "OR s.supplier_name LIKE ? OR m.status LIKE ?) " +
                            "ORDER BY m.medicine_name",
                    selectedMedicineId,
                    selectedMedicineCard,
                    name, desc, price, expiry, categoryBox, supplierBox, status,
                    k, k, k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            clearMedicineForm(name, desc, price, expiry, categoryBox, supplierBox, status);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                Date expiryDate = requireDate(expiry, "Expiry date");
                executeUpdate(
                        "INSERT INTO medicines (medicine_name, description, selling_price, expiry_date, category_id, supplier_id, status) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                        name.getText().trim(),
                        desc.getText().trim(),
                        Double.parseDouble(price.getText().trim()),
                        expiryDate,
                        selectedOptionId(categoryBox, "Category"),
                        selectedOptionId(supplierBox, "Supplier"),
                        status.getValue()
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Medicine added successfully.");
                clearMedicineForm(name, desc, price, expiry, categoryBox, supplierBox, status);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedMedicineId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a medicine card first.");
                return;
            }

            try {
                Date expiryDate = requireDate(expiry, "Expiry date");
                executeUpdate(
                        "UPDATE medicines SET medicine_name=?, description=?, selling_price=?, expiry_date=?, " +
                        "category_id=?, supplier_id=?, status=? WHERE medicine_id=?",
                        name.getText().trim(),
                        desc.getText().trim(),
                        Double.parseDouble(price.getText().trim()),
                        expiryDate,
                        selectedOptionId(categoryBox, "Category"),
                        selectedOptionId(supplierBox, "Supplier"),
                        status.getValue(),
                        selectedMedicineId[0]
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Selected medicine updated successfully.");
                clearMedicineForm(name, desc, price, expiry, categoryBox, supplierBox, status);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedMedicineId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a medicine card first.");
                return;
            }

            try {
                executeUpdate("UPDATE medicines SET status = 'INACTIVE' WHERE medicine_id = ?", selectedMedicineId[0]);

                alert(Alert.AlertType.INFORMATION, "Success",
                        "Selected medicine was deactivated successfully and removed from the active medicines cards.");

                clearMedicineForm(name, desc, price, expiry, categoryBox, supplierBox, status);
                search.clear();
                refresh.run();

            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Delete Failed", ex.getMessage());
            }
        });

        clear.setOnAction(e -> {
            clearMedicineForm(name, desc, price, expiry, categoryBox, supplierBox, status);
            selectedMedicineId[0] = -1;
            if (selectedMedicineCard[0] != null) {
                selectedMedicineCard[0].setStyle((String) selectedMedicineCard[0].getUserData());
                selectedMedicineCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, medicineCards);
        setCenterWithFixedRight(page, crudSidePanel("Medicine Form", form, actions));
    }

    private void loadMedicineCards(FlowPane container, String sql,
                                   int[] selectedMedicineId, VBox[] selectedMedicineCard,
                                   TextField name, TextField desc, TextField price, DatePicker expiry,
                                   ComboBox<String> categoryBox, ComboBox<String> supplierBox, ComboBox<String> status,
                                   Object... params) {
        container.getChildren().clear();
        selectedMedicineId[0] = -1;
        selectedMedicineCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;

                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String medName = safe(rs.getString("Medicine"));
                    String description = safe(rs.getString("Description"));
                    String categoryId = safe(rs.getString("Category_ID"));
                    String category = safe(rs.getString("Category"));
                    String supplierId = safe(rs.getString("Supplier_ID"));
                    String supplier = safe(rs.getString("Supplier"));
                    String sellingPrice = safe(rs.getString("Price"));
                    String expiryDate = safe(rs.getString("Expiry_Date"));
                    String medStatus = safe(rs.getString("Status"));

                    VBox card = medicineCard(id, medName, description, category, supplier, sellingPrice, expiryDate, medStatus);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedMedicineCard[0] != null) {
                            selectedMedicineCard[0].setStyle((String) selectedMedicineCard[0].getUserData());
                        }

                        selectedMedicineId[0] = id;
                        selectedMedicineCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        name.setText(medName);
                        desc.setText(description);
                        selectOptionById(categoryBox, categoryId);
                        selectOptionById(supplierBox, supplierId);
                        price.setText(sellingPrice);
                        setPickerValue(expiry, expiryDate);
                        status.setValue(medStatus);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No medicines found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private VBox medicineCard(int id, String name, String description, String category,
                              String supplier, String price, String expiry, String status) {
        VBox card = baseInfoCard(300, 235);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(name);
        title.setWrapText(true);
        title.setMaxWidth(195);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = statusPill(status);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(title, spacer, pill);

        Label descLabel = new Label(description.isEmpty() ? "No description" : description);
        descLabel.setWrapText(true);
        descLabel.setMaxHeight(48);
        descLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);

        details.add(cardField("ID", String.valueOf(id)), 0, 0);
        details.add(cardField("Price", price + " NIS"), 1, 0);
        details.add(cardField("Category", category), 0, 1);
        details.add(cardField("Supplier", supplier), 1, 1);
        details.add(cardField("Expiry", expiry), 0, 2);

        Label note = new Label("Click card to select for update/delete");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, descLabel, details, note);
        return card;
    }

    private void clearMedicineForm(TextField name, TextField desc, TextField price, DatePicker expiry,
                                   ComboBox<String> categoryBox, ComboBox<String> supplierBox, ComboBox<String> status) {
        name.clear();
        desc.clear();
        price.clear();
        expiry.setValue(null);
        categoryBox.getSelectionModel().clearSelection();
        supplierBox.getSelectionModel().clearSelection();
        status.setValue("ACTIVE");
    }

    // =========================================================
    // CUSTOMERS PAGE
    // =========================================================
    private void showCustomers() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Customers Management");
        title.setStyle(TITLE);

        Label sub = new Label("Filter Search, add, update, and delete customers. Customers are displayed as clean cards instead of a table.");
        sub.setStyle(SUBTITLE);

        TextField search = new TextField();
        search.setPromptText("Filter Search: name / phone / email / address / gender");
        search.setPrefWidth(420);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Total Customers", one("SELECT COUNT(*) FROM customers")),
                summaryCard("Male Customers", one("SELECT COUNT(*) FROM customers WHERE gender='MALE'")),
                summaryCard("Female Customers", one("SELECT COUNT(*) FROM customers WHERE gender='FEMALE'")),
                summaryCard("Customers With Sales", one("SELECT COUNT(DISTINCT customer_id) FROM sales"))
        );

        FlowPane customerCards = new FlowPane(14, 14);
        customerCards.setPadding(new Insets(5, 0, 5, 0));
        customerCards.setPrefWrapLength(700);

        TextField first = new TextField();
        first.setPromptText("First name");

        TextField last = new TextField();
        last.setPromptText("Last name");

        TextField phone = new TextField();
        phone.setPromptText("Phone");

        TextField address = new TextField();
        address.setPromptText("Address");

        TextField email = new TextField();
        email.setPromptText("Email");

        ComboBox<String> gender = new ComboBox<>();
        gender.getItems().addAll("MALE", "FEMALE");
        gender.setPrefWidth(180);

        DatePicker dob = new DatePicker();
        dob.setPromptText("Date of birth");

        GridPane form = formGrid();
        form.add(new Label("First Name"), 0, 0);
        form.add(first, 1, 0);
        form.add(new Label("Last Name"), 0, 1);
        form.add(last, 1, 1);
        form.add(new Label("Phone"), 0, 2);
        form.add(phone, 1, 2);
        form.add(new Label("Address"), 0, 3);
        form.add(address, 1, 3);
        form.add(new Label("Email"), 0, 4);
        form.add(email, 1, 4);
        form.add(new Label("Gender"), 0, 5);
        form.add(gender, 1, 5);
        form.add(new Label("Date of Birth"), 0, 6);
        form.add(dob, 1, 6);

        Button add = new Button("Add");
        add.setStyle(GREEN);
        Button update = new Button("Update Selected");
        update.setStyle(BTN);
        Button delete = new Button("Delete Selected");
        delete.setStyle(RED);
        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedCustomerId = {-1};
        final VBox[] selectedCustomerCard = {null};

        String selectSql =
                "SELECT customer_id AS ID, first_name AS First_Name, last_name AS Last_Name, " +
                "phone_number AS Phone, address AS Address, email AS Email, gender AS Gender, date_of_birth AS Date_Of_Birth " +
                "FROM customers ";

        Runnable refresh = () -> loadCustomerCards(
                customerCards,
                selectSql + "ORDER BY first_name, last_name",
                selectedCustomerId,
                selectedCustomerCard,
                first, last, phone, address, email, gender, dob
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadCustomerCards(
                    customerCards,
                    selectSql +
                            "WHERE first_name LIKE ? OR last_name LIKE ? OR phone_number LIKE ? OR address LIKE ? OR email LIKE ? OR gender LIKE ? " +
                            "ORDER BY first_name, last_name",
                    selectedCustomerId,
                    selectedCustomerCard,
                    first, last, phone, address, email, gender, dob,
                    k, k, k, k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            clearCustomerForm(first, last, phone, address, email, gender, dob);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                executeUpdate(
                        "INSERT INTO customers (first_name, last_name, phone_number, address, email, gender, date_of_birth) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                        first.getText().trim(),
                        last.getText().trim(),
                        phone.getText().trim(),
                        address.getText().trim(),
                        email.getText().trim(),
                        gender.getValue(),
                        optionalDate(dob)
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Customer added successfully.");
                clearCustomerForm(first, last, phone, address, email, gender, dob);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedCustomerId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a customer card first.");
                return;
            }

            try {
                executeUpdate(
                        "UPDATE customers SET first_name=?, last_name=?, phone_number=?, address=?, email=?, gender=?, date_of_birth=? " +
                        "WHERE customer_id=?",
                        first.getText().trim(),
                        last.getText().trim(),
                        phone.getText().trim(),
                        address.getText().trim(),
                        email.getText().trim(),
                        gender.getValue(),
                        optionalDate(dob),
                        selectedCustomerId[0]
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Selected customer updated successfully.");
                clearCustomerForm(first, last, phone, address, email, gender, dob);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedCustomerId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a customer card first.");
                return;
            }

            try {
                executeUpdate("DELETE FROM customers WHERE customer_id=?", selectedCustomerId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected customer deleted successfully.");
                clearCustomerForm(first, last, phone, address, email, gender, dob);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Delete Failed", "Cannot delete if customer has sales.\n" + ex.getMessage());
            }
        });

        clear.setOnAction(e -> {
            clearCustomerForm(first, last, phone, address, email, gender, dob);
            selectedCustomerId[0] = -1;
            if (selectedCustomerCard[0] != null) {
                selectedCustomerCard[0].setStyle((String) selectedCustomerCard[0].getUserData());
                selectedCustomerCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, customerCards);
        setCenterWithFixedRight(page, crudSidePanel("Customer Form", form, actions));
    }

    private void loadCustomerCards(FlowPane container, String sql,
                                   int[] selectedCustomerId, VBox[] selectedCustomerCard,
                                   TextField first, TextField last, TextField phone,
                                   TextField address, TextField email, ComboBox<String> gender, DatePicker dob,
                                   Object... params) {
        container.getChildren().clear();
        selectedCustomerId[0] = -1;
        selectedCustomerCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;

                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String firstName = safe(rs.getString("First_Name"));
                    String lastName = safe(rs.getString("Last_Name"));
                    String phoneNumber = safe(rs.getString("Phone"));
                    String customerAddress = safe(rs.getString("Address"));
                    String customerEmail = safe(rs.getString("Email"));
                    String customerGender = safe(rs.getString("Gender"));
                    String birthDate = safe(rs.getString("Date_Of_Birth"));

                    VBox card = customerCard(id, firstName, lastName, phoneNumber, customerAddress, customerEmail, customerGender, birthDate);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedCustomerCard[0] != null) {
                            selectedCustomerCard[0].setStyle((String) selectedCustomerCard[0].getUserData());
                        }

                        selectedCustomerId[0] = id;
                        selectedCustomerCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        first.setText(firstName);
                        last.setText(lastName);
                        phone.setText(phoneNumber);
                        address.setText(customerAddress);
                        email.setText(customerEmail);
                        gender.setValue(customerGender);
                        setPickerValue(dob, birthDate);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No customers found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private VBox customerCard(int id, String firstName, String lastName, String phone,
                              String address, String email, String gender, String dob) {
        VBox card = baseInfoCard(300, 225);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        String fullName = (firstName + " " + lastName).trim();

        Label title = new Label(fullName.isEmpty() ? "Unknown Customer" : fullName);
        title.setWrapText(true);
        title.setMaxWidth(195);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = genderPill(gender);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(title, spacer, pill);

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);

        details.add(cardField("ID", String.valueOf(id)), 0, 0);
        details.add(cardField("Phone", phone), 1, 0);
        details.add(cardField("Email", email), 0, 1);
        details.add(cardField("Address", address), 1, 1);
        details.add(cardField("Birth Date", dob), 0, 2);

        Label note = new Label("Click card to select for update/delete");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, details, note);
        return card;
    }

    private VBox baseInfoCard(double width, double minHeight) {
        VBox card = new VBox(10);
        card.setPrefWidth(width);
        card.setMinHeight(minHeight);
        card.setAlignment(Pos.TOP_LEFT);

        String baseStyle =
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-padding: 16;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-width: 1;" +
                "-fx-border-radius: 16;" +
                "-fx-effect: dropshadow(gaussian, rgba(15,23,42,0.10), 12, 0, 0, 3);" +
                "-fx-cursor: hand;";

        card.setStyle(baseStyle);
        card.setUserData(baseStyle);
        return card;
    }

    private VBox cardField(String title, String value) {
        VBox box = new VBox(2);
        box.setPrefWidth(130);

        Label t = new Label(title);
        t.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8; -fx-font-weight: bold;");

        Label v = new Label(value == null || value.trim().isEmpty() ? "-" : value);
        v.setWrapText(true);
        v.setMaxWidth(130);
        v.setStyle("-fx-font-size: 12px; -fx-text-fill: #334155;");

        box.getChildren().addAll(t, v);
        return box;
    }

    private Label statusPill(String value) {
        Label pill = new Label(value == null || value.trim().isEmpty() ? "UNKNOWN" : value);
        pill.setStyle(
                "-fx-background-color: " + ("ACTIVE".equalsIgnoreCase(value) ? "#dcfce7" : "#fee2e2") + ";" +
                "-fx-text-fill: " + ("ACTIVE".equalsIgnoreCase(value) ? "#166534" : "#991b1b") + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 8;" +
                "-fx-background-radius: 999;"
        );
        return pill;
    }

    private Label genderPill(String value) {
        boolean female = "FEMALE".equalsIgnoreCase(value);
        Label pill = new Label(value == null || value.trim().isEmpty() ? "N/A" : value);
        pill.setStyle(
                "-fx-background-color: " + (female ? "#fce7f3" : "#dbeafe") + ";" +
                "-fx-text-fill: " + (female ? "#9d174d" : "#1d4ed8") + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 8;" +
                "-fx-background-radius: 999;"
        );
        return pill;
    }

    private Label coloredPill(String value) {
        String text = value == null || value.trim().isEmpty() ? "UNKNOWN" : value.trim();
        String bg = "#e0f2fe";
        String fg = "#0369a1";

        String u = text.toUpperCase();
        if (u.contains("LOW") || u.contains("PENDING")) {
            bg = "#fef3c7";
            fg = "#92400e";
        } else if (u.contains("OUT") || u.contains("EXPIRED") || u.contains("CANCELLED") || u.contains("INACTIVE")) {
            bg = "#fee2e2";
            fg = "#991b1b";
        } else if (u.contains("COMPLETED") || u.contains("RECEIVED") || u.contains("AVAILABLE") || u.contains("ACTIVE") || u.contains("PAID")) {
            bg = "#dcfce7";
            fg = "#166534";
        }

        Label pill = new Label(text);
        pill.setStyle(
                "-fx-background-color: " + bg + ";" +
                "-fx-text-fill: " + fg + ";" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 8;" +
                "-fx-background-radius: 999;"
        );
        return pill;
    }

    private VBox emptyCardsMessage(String message) {
        VBox box = new VBox(8);
        box.setPrefWidth(640);
        box.setMinHeight(150);
        box.setAlignment(Pos.CENTER);
        box.setStyle(
                "-fx-background-color: white;" +
                "-fx-background-radius: 16;" +
                "-fx-padding: 24;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 16;"
        );

        Label title = new Label("No Data");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #64748b;");

        Label sub = new Label(message);
        sub.setStyle("-fx-font-size: 14px; -fx-text-fill: #94a3b8;");

        box.getChildren().addAll(title, sub);
        return box;
    }

    private String safe(String value) {
        return value == null ? "" : value;
    }

    private void clearCustomerForm(TextField first, TextField last, TextField phone, TextField address,
                                   TextField email, ComboBox<String> gender, DatePicker dob) {
        first.clear();
        last.clear();
        phone.clear();
        address.clear();
        email.clear();
        gender.setValue(null);
        dob.setValue(null);
    }

    // =========================================================
    // SUPPLIERS PAGE
    // =========================================================
    private void showSuppliers() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Suppliers Management");
        title.setStyle(TITLE);

        Label sub = new Label("Filter Search, add, update, and delete suppliers. Suppliers are displayed as clean cards instead of a table.");
        sub.setStyle(SUBTITLE);

        TextField search = new TextField();
        search.setPromptText("Filter Search: supplier / company / phone / email / address");
        search.setPrefWidth(420);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Total Suppliers", one("SELECT COUNT(*) FROM suppliers")),
                summaryCard("Companies", one("SELECT COUNT(DISTINCT company_name) FROM suppliers WHERE company_name IS NOT NULL AND company_name <> ''")),
                summaryCard("Suppliers With Medicines", one("SELECT COUNT(DISTINCT supplier_id) FROM medicines")),
                summaryCard("Suppliers With Purchases", one("SELECT COUNT(DISTINCT supplier_id) FROM purchases"))
        );

        FlowPane supplierCards = new FlowPane(14, 14);
        supplierCards.setPadding(new Insets(5, 0, 5, 0));
        supplierCards.setPrefWrapLength(700);

        TextField supplierName = new TextField();
        supplierName.setPromptText("Supplier name");

        TextField first = new TextField();
        first.setPromptText("First name");

        TextField last = new TextField();
        last.setPromptText("Last name");

        TextField phone = new TextField();
        phone.setPromptText("Phone");

        TextField address = new TextField();
        address.setPromptText("Address");

        TextField email = new TextField();
        email.setPromptText("Email");

        TextField company = new TextField();
        company.setPromptText("Company");

        GridPane form = formGrid();
        form.add(new Label("Supplier Name"), 0, 0);
        form.add(supplierName, 1, 0);
        form.add(new Label("Company"), 0, 1);
        form.add(company, 1, 1);
        form.add(new Label("First Name"), 0, 2);
        form.add(first, 1, 2);
        form.add(new Label("Last Name"), 0, 3);
        form.add(last, 1, 3);
        form.add(new Label("Phone"), 0, 4);
        form.add(phone, 1, 4);
        form.add(new Label("Email"), 0, 5);
        form.add(email, 1, 5);
        form.add(new Label("Address"), 0, 6);
        form.add(address, 1, 6);

        Button add = new Button("Add");
        add.setStyle(GREEN);
        Button update = new Button("Update Selected");
        update.setStyle(BTN);
        Button delete = new Button("Delete Selected");
        delete.setStyle(RED);
        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedSupplierId = {-1};
        final VBox[] selectedSupplierCard = {null};

        String selectSql =
                "SELECT supplier_id AS ID, supplier_name AS Supplier_Name, first_name AS First_Name, last_name AS Last_Name, " +
                "phone_number AS Phone, address AS Address, email AS Email, company_name AS Company " +
                "FROM suppliers ";

        Runnable refresh = () -> loadSupplierCards(
                supplierCards,
                selectSql + "ORDER BY supplier_name",
                selectedSupplierId,
                selectedSupplierCard,
                supplierName, first, last, phone, address, email, company
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadSupplierCards(
                    supplierCards,
                    selectSql +
                            "WHERE supplier_name LIKE ? OR first_name LIKE ? OR last_name LIKE ? OR phone_number LIKE ? " +
                            "OR address LIKE ? OR email LIKE ? OR company_name LIKE ? " +
                            "ORDER BY supplier_name",
                    selectedSupplierId,
                    selectedSupplierCard,
                    supplierName, first, last, phone, address, email, company,
                    k, k, k, k, k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            clearSupplierForm(supplierName, first, last, phone, address, email, company);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                executeUpdate(
                        "INSERT INTO suppliers (supplier_name, first_name, last_name, phone_number, address, email, company_name) " +
                        "VALUES (?, ?, ?, ?, ?, ?, ?)",
                        supplierName.getText().trim(), first.getText().trim(), last.getText().trim(),
                        phone.getText().trim(), address.getText().trim(), email.getText().trim(), company.getText().trim()
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Supplier added successfully.");
                clearSupplierForm(supplierName, first, last, phone, address, email, company);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedSupplierId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a supplier card first.");
                return;
            }

            try {
                executeUpdate(
                        "UPDATE suppliers SET supplier_name=?, first_name=?, last_name=?, phone_number=?, address=?, email=?, company_name=? " +
                        "WHERE supplier_id=?",
                        supplierName.getText().trim(), first.getText().trim(), last.getText().trim(),
                        phone.getText().trim(), address.getText().trim(), email.getText().trim(), company.getText().trim(),
                        selectedSupplierId[0]
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Selected supplier updated successfully.");
                clearSupplierForm(supplierName, first, last, phone, address, email, company);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedSupplierId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a supplier card first.");
                return;
            }

            try {
                executeUpdate("DELETE FROM suppliers WHERE supplier_id=?", selectedSupplierId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected supplier deleted successfully.");
                clearSupplierForm(supplierName, first, last, phone, address, email, company);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Delete Failed", "Cannot delete if supplier is used by medicines or purchases.\n" + ex.getMessage());
            }
        });

        clear.setOnAction(e -> {
            clearSupplierForm(supplierName, first, last, phone, address, email, company);
            selectedSupplierId[0] = -1;
            if (selectedSupplierCard[0] != null) {
                selectedSupplierCard[0].setStyle((String) selectedSupplierCard[0].getUserData());
                selectedSupplierCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, supplierCards);
        setCenterWithFixedRight(page, crudSidePanel("Supplier Form", form, actions));
    }

    private void loadSupplierCards(FlowPane container, String sql,
                                   int[] selectedSupplierId, VBox[] selectedSupplierCard,
                                   TextField supplierName, TextField first, TextField last,
                                   TextField phone, TextField address, TextField email, TextField company,
                                   Object... params) {
        container.getChildren().clear();
        selectedSupplierId[0] = -1;
        selectedSupplierCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;

                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String supName = safe(rs.getString("Supplier_Name"));
                    String firstName = safe(rs.getString("First_Name"));
                    String lastName = safe(rs.getString("Last_Name"));
                    String phoneNumber = safe(rs.getString("Phone"));
                    String supplierAddress = safe(rs.getString("Address"));
                    String supplierEmail = safe(rs.getString("Email"));
                    String companyName = safe(rs.getString("Company"));

                    VBox card = supplierCard(id, supName, firstName, lastName, phoneNumber, supplierAddress, supplierEmail, companyName);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedSupplierCard[0] != null) {
                            selectedSupplierCard[0].setStyle((String) selectedSupplierCard[0].getUserData());
                        }

                        selectedSupplierId[0] = id;
                        selectedSupplierCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        supplierName.setText(supName);
                        first.setText(firstName);
                        last.setText(lastName);
                        phone.setText(phoneNumber);
                        address.setText(supplierAddress);
                        email.setText(supplierEmail);
                        company.setText(companyName);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No suppliers found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private VBox supplierCard(int id, String supplierName, String firstName, String lastName,
                              String phone, String address, String email, String company) {
        VBox card = baseInfoCard(300, 225);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(supplierName.isEmpty() ? "Unknown Supplier" : supplierName);
        title.setWrapText(true);
        title.setMaxWidth(205);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = new Label("SUPPLIER");
        pill.setStyle(
                "-fx-background-color: #e0f2fe;" +
                "-fx-text-fill: #0369a1;" +
                "-fx-font-size: 11px;" +
                "-fx-font-weight: bold;" +
                "-fx-padding: 4 8;" +
                "-fx-background-radius: 999;"
        );

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        header.getChildren().addAll(title, spacer, pill);

        String contactName = (firstName + " " + lastName).trim();
        Label contact = new Label(contactName.isEmpty() ? "No contact person" : "Contact: " + contactName);
        contact.setWrapText(true);
        contact.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);

        details.add(cardField("ID", String.valueOf(id)), 0, 0);
        details.add(cardField("Company", company), 1, 0);
        details.add(cardField("Phone", phone), 0, 1);
        details.add(cardField("Email", email), 1, 1);
        details.add(cardField("Address", address), 0, 2);

        Label note = new Label("Click card to select for update/delete");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, contact, details, note);
        return card;
    }

    private void clearSupplierForm(TextField supplierName, TextField first, TextField last, TextField phone,
                                   TextField address, TextField email, TextField company) {
        supplierName.clear();
        first.clear();
        last.clear();
        phone.clear();
        address.clear();
        email.clear();
        company.clear();
    }

    // =========================================================
    // WAREHOUSES PAGE
    // =========================================================
    private void showWarehouses() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Warehouses Management");
        title.setStyle(TITLE);

        Label sub = new Label("Filter Search, add, update, and delete warehouses. Warehouses are displayed as clean cards.");
        sub.setStyle(SUBTITLE);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Total Warehouses", one("SELECT COUNT(*) FROM warehouses")),
                summaryCard("Total Capacity", one("SELECT COALESCE(SUM(capacity), 0) FROM warehouses")),
                summaryCard("Branches Used", one("SELECT COUNT(DISTINCT branch_id) FROM warehouses")),
                summaryCard("Inventory Records", one("SELECT COUNT(*) FROM inventory"))
        );

        TextField search = new TextField();
        search.setPromptText("Filter Search: warehouse / location / branch");
        search.setPrefWidth(500);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        FlowPane warehouseCards = new FlowPane(14, 14);
        warehouseCards.setPadding(new Insets(5, 0, 5, 0));
        warehouseCards.setPrefWrapLength(700);

        TextField name = new TextField();
        name.setPromptText("Warehouse name");

        TextField location = new TextField();
        location.setPromptText("Location");

        TextField capacity = new TextField();
        capacity.setPromptText("Capacity");

        ComboBox<String> branchBox = new ComboBox<>();
        branchBox.setPromptText("Choose branch");
        branchBox.setPrefWidth(220);
        loadReferenceOptions(branchBox,
                "SELECT branch_id, branch_name FROM branches ORDER BY branch_name");

        GridPane form = formGrid();
        form.add(new Label("Warehouse Name"), 0, 0);
        form.add(name, 1, 0);
        form.add(new Label("Location"), 0, 1);
        form.add(location, 1, 1);
        form.add(new Label("Capacity"), 0, 2);
        form.add(capacity, 1, 2);
        form.add(new Label("Branch"), 0, 3);
        form.add(branchBox, 1, 3);

        Button add = new Button("Add");
        add.setStyle(GREEN);
        Button update = new Button("Update Selected");
        update.setStyle(BTN);
        Button delete = new Button("Delete Selected");
        delete.setStyle(RED);
        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedWarehouseId = {-1};
        final VBox[] selectedWarehouseCard = {null};

        String selectSql =
                "SELECT w.warehouse_id AS ID, w.warehouse_name AS Warehouse, w.location AS Location, " +
                "w.capacity AS Capacity, w.branch_id AS Branch_ID, b.branch_name AS Branch " +
                "FROM warehouses w JOIN branches b ON w.branch_id = b.branch_id ";

        Runnable refresh = () -> loadWarehouseCards(
                warehouseCards,
                selectSql + "ORDER BY w.warehouse_name",
                selectedWarehouseId,
                selectedWarehouseCard,
                name, location, capacity, branchBox
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadWarehouseCards(
                    warehouseCards,
                    selectSql +
                            "WHERE w.warehouse_name LIKE ? OR w.location LIKE ? OR b.branch_name LIKE ? " +
                            "ORDER BY w.warehouse_name",
                    selectedWarehouseId,
                    selectedWarehouseCard,
                    name, location, capacity, branchBox,
                    k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            loadReferenceOptions(branchBox, "SELECT branch_id, branch_name FROM branches ORDER BY branch_name");
            clearWarehouseForm(name, location, capacity, branchBox);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                executeUpdate(
                        "INSERT INTO warehouses (warehouse_name, location, capacity, branch_id) VALUES (?, ?, ?, ?)",
                        name.getText().trim(), location.getText().trim(),
                        Integer.parseInt(capacity.getText().trim()), selectedOptionId(branchBox, "Branch")
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Warehouse added successfully.");
                clearWarehouseForm(name, location, capacity, branchBox);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedWarehouseId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a warehouse card first.");
                return;
            }
            try {
                executeUpdate(
                        "UPDATE warehouses SET warehouse_name=?, location=?, capacity=?, branch_id=? WHERE warehouse_id=?",
                        name.getText().trim(), location.getText().trim(),
                        Integer.parseInt(capacity.getText().trim()), selectedOptionId(branchBox, "Branch"),
                        selectedWarehouseId[0]
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Selected warehouse updated successfully.");
                clearWarehouseForm(name, location, capacity, branchBox);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedWarehouseId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a warehouse card first.");
                return;
            }
            try {
                executeUpdate("DELETE FROM warehouses WHERE warehouse_id=?", selectedWarehouseId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected warehouse deleted successfully.");
                clearWarehouseForm(name, location, capacity, branchBox);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Delete Failed", "Cannot delete if warehouse is used in inventory.\n" + friendlyDbMessage(ex));
            }
        });

        clear.setOnAction(e -> {
            clearWarehouseForm(name, location, capacity, branchBox);
            selectedWarehouseId[0] = -1;
            if (selectedWarehouseCard[0] != null) {
                selectedWarehouseCard[0].setStyle((String) selectedWarehouseCard[0].getUserData());
                selectedWarehouseCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, warehouseCards);
        setCenterWithFixedRight(page, crudSidePanel("Warehouse Form", form, actions));
    }

    private void loadWarehouseCards(FlowPane container, String sql,
                                    int[] selectedWarehouseId, VBox[] selectedWarehouseCard,
                                    TextField name, TextField location, TextField capacity, ComboBox<String> branchBox,
                                    Object... params) {
        container.getChildren().clear();
        selectedWarehouseId[0] = -1;
        selectedWarehouseCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String warehouse = safe(rs.getString("Warehouse"));
                    String loc = safe(rs.getString("Location"));
                    String cap = safe(rs.getString("Capacity"));
                    String branchIdValue = safe(rs.getString("Branch_ID"));
                    String branch = safe(rs.getString("Branch"));

                    VBox card = warehouseCard(id, warehouse, loc, cap, branchIdValue, branch);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedWarehouseCard[0] != null) {
                            selectedWarehouseCard[0].setStyle((String) selectedWarehouseCard[0].getUserData());
                        }
                        selectedWarehouseId[0] = id;
                        selectedWarehouseCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        name.setText(warehouse);
                        location.setText(loc);
                        capacity.setText(cap);
                        selectOptionById(branchBox, branchIdValue);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No warehouses found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", friendlyDbMessage(e));
        }
    }

    private VBox warehouseCard(int id, String name, String location, String capacity, String branchId, String branch) {
        VBox card = baseInfoCard(300, 205);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(name.isEmpty() ? "Unknown Warehouse" : name);
        title.setWrapText(true);
        title.setMaxWidth(205);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = coloredPill("WAREHOUSE");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(title, spacer, pill);

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);
        details.add(cardField("ID", String.valueOf(id)), 0, 0);
        details.add(cardField("Capacity", capacity), 1, 0);
        details.add(cardField("Branch ID", branchId), 0, 1);
        details.add(cardField("Branch", branch), 1, 1);
        details.add(cardField("Location", location), 0, 2);

        Label note = new Label("Click card to select for update/delete");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, details, note);
        return card;
    }

    private void clearWarehouseForm(TextField name, TextField location, TextField capacity, ComboBox<String> branchBox) {
        name.clear();
        location.clear();
        capacity.clear();
        branchBox.getSelectionModel().clearSelection();
    }

    // =========================================================
    // INVENTORY PAGE
    // =========================================================
    private void showInventory() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Inventory / Stock Management");
        title.setStyle(TITLE);

        Label sub = new Label("Filter Search, add, update, and delete stock records. Inventory is displayed as clean cards.");
        sub.setStyle(SUBTITLE);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Inventory Records", one("SELECT COUNT(*) FROM inventory")),
                summaryCard("Total Units", one("SELECT COALESCE(SUM(quantity), 0) FROM inventory")),
                summaryCard("Low Stock", one("SELECT COUNT(*) FROM inventory WHERE quantity <= reorder_level")),
                summaryCard("Out Of Stock", one("SELECT COUNT(*) FROM inventory WHERE quantity = 0"))
        );

        TextField search = new TextField();
        search.setPromptText("Filter Search: warehouse / medicine / status");
        search.setPrefWidth(500);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        FlowPane inventoryCards = new FlowPane(14, 14);
        inventoryCards.setPadding(new Insets(5, 0, 5, 0));
        inventoryCards.setPrefWrapLength(700);

        ComboBox<String> warehouseBox = new ComboBox<>();
        warehouseBox.setPromptText("Choose warehouse");
        warehouseBox.setPrefWidth(220);
        loadReferenceOptions(warehouseBox,
                "SELECT warehouse_id, warehouse_name FROM warehouses ORDER BY warehouse_name");

        ComboBox<String> medicineBox = new ComboBox<>();
        medicineBox.setPromptText("Choose medicine");
        medicineBox.setPrefWidth(220);
        loadReferenceOptions(medicineBox,
                "SELECT medicine_id, medicine_name FROM medicines WHERE status='ACTIVE' ORDER BY medicine_name");

        TextField quantity = new TextField();
        quantity.setPromptText("Quantity");

        TextField reorder = new TextField();
        reorder.setPromptText("Reorder Level");

        ComboBox<String> status = new ComboBox<>();
        status.getItems().addAll("AVAILABLE", "LOW_STOCK", "OUT_OF_STOCK", "EXPIRED");
        status.setValue("AVAILABLE");
        status.setPrefWidth(180);

        GridPane form = formGrid();
        form.add(new Label("Warehouse"), 0, 0);
        form.add(warehouseBox, 1, 0);
        form.add(new Label("Medicine"), 0, 1);
        form.add(medicineBox, 1, 1);
        form.add(new Label("Quantity"), 0, 2);
        form.add(quantity, 1, 2);
        form.add(new Label("Reorder Level"), 0, 3);
        form.add(reorder, 1, 3);
        form.add(new Label("Status"), 0, 4);
        form.add(status, 1, 4);

        Button add = new Button("Add");
        add.setStyle(GREEN);
        Button update = new Button("Update Selected");
        update.setStyle(BTN);
        Button delete = new Button("Delete Selected");
        delete.setStyle(RED);
        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedWarehouseId = {-1};
        final int[] selectedMedicineId = {-1};
        final VBox[] selectedInventoryCard = {null};

        String selectSql =
                "SELECT i.warehouse_id AS Warehouse_ID, w.warehouse_name AS Warehouse, " +
                "i.medicine_id AS Medicine_ID, m.medicine_name AS Medicine, " +
                "i.quantity AS Quantity, i.reorder_level AS Reorder_Level, i.status AS Status, " +
                "m.expiry_date AS Expiry_Date, i.last_updated AS Last_Updated " +
                "FROM inventory i " +
                "JOIN warehouses w ON i.warehouse_id = w.warehouse_id " +
                "JOIN medicines m ON i.medicine_id = m.medicine_id ";

        Runnable refresh = () -> loadInventoryCards(
                inventoryCards,
                selectSql + "ORDER BY w.warehouse_name, m.medicine_name",
                selectedWarehouseId,
                selectedMedicineId,
                selectedInventoryCard,
                warehouseBox, medicineBox, quantity, reorder, status
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadInventoryCards(
                    inventoryCards,
                    selectSql +
                            "WHERE w.warehouse_name LIKE ? OR m.medicine_name LIKE ? OR i.status LIKE ? " +
                            "ORDER BY w.warehouse_name, m.medicine_name",
                    selectedWarehouseId,
                    selectedMedicineId,
                    selectedInventoryCard,
                    warehouseBox, medicineBox, quantity, reorder, status,
                    k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            loadReferenceOptions(warehouseBox, "SELECT warehouse_id, warehouse_name FROM warehouses ORDER BY warehouse_name");
            loadReferenceOptions(medicineBox, "SELECT medicine_id, medicine_name FROM medicines WHERE status='ACTIVE' ORDER BY medicine_name");
            clearInventoryForm(warehouseBox, medicineBox, quantity, reorder, status);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                executeUpdate(
                        "INSERT INTO inventory (warehouse_id, medicine_id, quantity, reorder_level, status) VALUES (?, ?, ?, ?, ?)",
                        selectedOptionId(warehouseBox, "Warehouse"),
                        selectedOptionId(medicineBox, "Medicine"),
                        Integer.parseInt(quantity.getText().trim()),
                        Integer.parseInt(reorder.getText().trim()),
                        status.getValue()
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Inventory record added successfully.");
                clearInventoryForm(warehouseBox, medicineBox, quantity, reorder, status);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedWarehouseId[0] == -1 || selectedMedicineId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select an inventory card first.");
                return;
            }

            try {
                executeUpdate(
                        "UPDATE inventory SET warehouse_id=?, medicine_id=?, quantity=?, reorder_level=?, status=? " +
                        "WHERE warehouse_id=? AND medicine_id=?",
                        selectedOptionId(warehouseBox, "Warehouse"),
                        selectedOptionId(medicineBox, "Medicine"),
                        Integer.parseInt(quantity.getText().trim()),
                        Integer.parseInt(reorder.getText().trim()),
                        status.getValue(),
                        selectedWarehouseId[0],
                        selectedMedicineId[0]
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Selected inventory record updated successfully.");
                clearInventoryForm(warehouseBox, medicineBox, quantity, reorder, status);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedWarehouseId[0] == -1 || selectedMedicineId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select an inventory card first.");
                return;
            }

            try {
                executeUpdate("DELETE FROM inventory WHERE warehouse_id=? AND medicine_id=?",
                        selectedWarehouseId[0],
                        selectedMedicineId[0]
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Selected inventory record deleted successfully.");
                clearInventoryForm(warehouseBox, medicineBox, quantity, reorder, status);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Delete Failed", friendlyDbMessage(ex));
            }
        });

        clear.setOnAction(e -> {
            clearInventoryForm(warehouseBox, medicineBox, quantity, reorder, status);
            selectedWarehouseId[0] = -1;
            selectedMedicineId[0] = -1;
            if (selectedInventoryCard[0] != null) {
                selectedInventoryCard[0].setStyle((String) selectedInventoryCard[0].getUserData());
                selectedInventoryCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, inventoryCards);
        setCenterWithFixedRight(page, crudSidePanel("Inventory Form", form, actions));
    }

    private void loadInventoryCards(FlowPane container, String sql,
                                    int[] selectedWarehouseId, int[] selectedMedicineId, VBox[] selectedInventoryCard,
                                    ComboBox<String> warehouseBox, ComboBox<String> medicineBox, TextField quantity, TextField reorder,
                                    ComboBox<String> status,
                                    Object... params) {
        container.getChildren().clear();
        selectedWarehouseId[0] = -1;
        selectedMedicineId[0] = -1;
        selectedInventoryCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int wId = rs.getInt("Warehouse_ID");
                    String warehouse = safe(rs.getString("Warehouse"));
                    int mId = rs.getInt("Medicine_ID");
                    String medicine = safe(rs.getString("Medicine"));
                    String qty = safe(rs.getString("Quantity"));
                    String reorderValue = safe(rs.getString("Reorder_Level"));
                    String statusValue = safe(rs.getString("Status"));
                    String expiry = safe(rs.getString("Expiry_Date"));
                    String lastUpdated = safe(rs.getString("Last_Updated"));

                    VBox card = inventoryCard(wId, warehouse, mId, medicine, qty, reorderValue, statusValue, expiry, lastUpdated);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedInventoryCard[0] != null) {
                            selectedInventoryCard[0].setStyle((String) selectedInventoryCard[0].getUserData());
                        }
                        selectedWarehouseId[0] = wId;
                        selectedMedicineId[0] = mId;
                        selectedInventoryCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        selectOptionById(warehouseBox, String.valueOf(wId));
                        selectOptionById(medicineBox, String.valueOf(mId));
                        quantity.setText(qty);
                        reorder.setText(reorderValue);
                        status.setValue(statusValue);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No inventory records found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", friendlyDbMessage(e));
        }
    }

    private VBox inventoryCard(int warehouseId, String warehouse, int medicineId, String medicine,
                               String quantity, String reorder, String status, String expiry, String lastUpdated) {
        VBox card = baseInfoCard(300, 235);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label(medicine.isEmpty() ? "Unknown Medicine" : medicine);
        title.setWrapText(true);
        title.setMaxWidth(210);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = coloredPill(status);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(title, spacer, pill);

        Label warehouseLabel = new Label(warehouse.isEmpty() ? "No warehouse" : "Warehouse: " + warehouse);
        warehouseLabel.setWrapText(true);
        warehouseLabel.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);
        details.add(cardField("Warehouse ID", String.valueOf(warehouseId)), 0, 0);
        details.add(cardField("Medicine ID", String.valueOf(medicineId)), 1, 0);
        details.add(cardField("Quantity", quantity), 0, 1);
        details.add(cardField("Reorder", reorder), 1, 1);
        details.add(cardField("Expiry", expiry), 0, 2);
        details.add(cardField("Updated", lastUpdated), 1, 2);

        Label note = new Label("Click card to select for update/delete");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, warehouseLabel, details, note);
        return card;
    }

    private void clearInventoryForm(ComboBox<String> warehouseBox, ComboBox<String> medicineBox, TextField quantity,
                                    TextField reorder, ComboBox<String> status) {
        warehouseBox.getSelectionModel().clearSelection();
        medicineBox.getSelectionModel().clearSelection();
        quantity.clear();
        reorder.clear();
        status.setValue("AVAILABLE");
    }

    // =========================================================
    // SALES PAGE
    // =========================================================
    private void showSales() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Sales Management");
        title.setStyle(TITLE);

        Label sub = new Label("Create sales using a transaction. Choose customer, pharmacist, branch, warehouse, and medicine from dropdowns.");
        sub.setStyle(SUBTITLE);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Total Sales", one("SELECT COUNT(*) FROM sales")),
                summaryCard("Completed", one("SELECT COUNT(*) FROM sales WHERE sale_status='COMPLETED'")),
                summaryCard("Revenue", one("SELECT COALESCE(SUM(total_amount), 0) FROM sales WHERE sale_status <> 'CANCELLED'")),
                summaryCard("Cancelled", one("SELECT COUNT(*) FROM sales WHERE sale_status='CANCELLED'"))
        );

        TextField search = new TextField();
        search.setPromptText("Filter Search: customer / pharmacist / branch / payment / status");
        search.setPrefWidth(500);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        FlowPane saleCards = new FlowPane(14, 14);
        saleCards.setPadding(new Insets(5, 0, 5, 0));
        saleCards.setPrefWrapLength(700);

        ComboBox<String> customerBox = new ComboBox<>();
        customerBox.setPromptText("Choose customer");
        customerBox.setPrefWidth(220);
        loadReferenceOptions(customerBox,
                "SELECT customer_id, CONCAT(first_name, ' ', last_name) FROM customers ORDER BY first_name, last_name");

        ComboBox<String> pharmacistBox = new ComboBox<>();
        pharmacistBox.setPromptText("Choose pharmacist");
        pharmacistBox.setPrefWidth(220);
        loadReferenceOptions(pharmacistBox,
                "SELECT pharmacist_id, CONCAT(first_name, ' ', last_name) FROM pharmacists ORDER BY first_name, last_name");

        ComboBox<String> branchBox = new ComboBox<>();
        branchBox.setPromptText("Choose branch");
        branchBox.setPrefWidth(220);
        loadReferenceOptions(branchBox,
                "SELECT branch_id, branch_name FROM branches ORDER BY branch_name");

        ComboBox<String> payment = new ComboBox<>();
        payment.getItems().addAll("CASH", "CARD", "INSURANCE", "MOBILE_PAYMENT", "OTHER");
        payment.setValue("CASH");
        payment.setPrefWidth(220);

        ComboBox<String> saleStatus = new ComboBox<>();
        saleStatus.getItems().addAll("COMPLETED", "CANCELLED", "REFUNDED");
        saleStatus.setValue("COMPLETED");
        saleStatus.setPrefWidth(220);

        ComboBox<String> warehouseBox = new ComboBox<>();
        warehouseBox.setPromptText("Choose warehouse");
        warehouseBox.setPrefWidth(220);
        loadReferenceOptions(warehouseBox,
                "SELECT warehouse_id, warehouse_name FROM warehouses ORDER BY warehouse_name");

        ComboBox<String> medicineBox = new ComboBox<>();
        medicineBox.setPromptText("Choose medicine");
        medicineBox.setPrefWidth(220);
        loadReferenceOptions(medicineBox,
                "SELECT medicine_id, medicine_name FROM medicines WHERE status='ACTIVE' ORDER BY medicine_name");

        TextField quantity = new TextField();
        quantity.setPromptText("Quantity");

        GridPane form = formGrid();
        form.add(new Label("Customer"), 0, 0);
        form.add(customerBox, 1, 0);
        form.add(new Label("Pharmacist"), 0, 1);
        form.add(pharmacistBox, 1, 1);
        form.add(new Label("Branch"), 0, 2);
        form.add(branchBox, 1, 2);
        form.add(new Label("Payment"), 0, 3);
        form.add(payment, 1, 3);
        form.add(new Label("Warehouse"), 0, 4);
        form.add(warehouseBox, 1, 4);
        form.add(new Label("Medicine"), 0, 5);
        form.add(medicineBox, 1, 5);
        form.add(new Label("Quantity"), 0, 6);
        form.add(quantity, 1, 6);
        form.add(new Label("Sale Status"), 0, 7);
        form.add(saleStatus, 1, 7);

        Button add = new Button("Add Sale");
        add.setStyle(GREEN);
        Button update = new Button("Update Selected Status/Payment");
        update.setStyle(BTN);
        Button delete = new Button("Delete / Cancel Selected");
        delete.setStyle(RED);
        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedSaleId = {-1};
        final VBox[] selectedSaleCard = {null};

        String selectSql = salesSelectSql();

        Runnable refresh = () -> loadSaleCards(
                saleCards,
                selectSql + " ORDER BY s.sale_date DESC",
                selectedSaleId,
                selectedSaleCard,
                customerBox, pharmacistBox, branchBox, payment, saleStatus
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadSaleCards(
                    saleCards,
                    selectSql +
                            " WHERE CONCAT(c.first_name, ' ', c.last_name) LIKE ? " +
                            "OR CONCAT(p.first_name, ' ', p.last_name) LIKE ? OR b.branch_name LIKE ? " +
                            "OR s.payment_method LIKE ? OR s.sale_status LIKE ? " +
                            "ORDER BY s.sale_date DESC",
                    selectedSaleId,
                    selectedSaleCard,
                    customerBox, pharmacistBox, branchBox, payment, saleStatus,
                    k, k, k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            reloadSaleDropdowns(customerBox, pharmacistBox, branchBox, warehouseBox, medicineBox);
            clearSalesForm(customerBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, payment, saleStatus);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                createSaleTransaction(
                        selectedOptionId(customerBox, "Customer"),
                        selectedOptionId(pharmacistBox, "Pharmacist"),
                        selectedOptionId(branchBox, "Branch"),
                        payment.getValue(),
                        selectedOptionId(warehouseBox, "Warehouse"),
                        selectedOptionId(medicineBox, "Medicine"),
                        Integer.parseInt(quantity.getText().trim())
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Sale created successfully with invoice and inventory update.");
                clearSalesForm(customerBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, payment, saleStatus);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Sale Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedSaleId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a sale card first.");
                return;
            }
            try {
                executeUpdate("UPDATE sales SET payment_method=?, sale_status=? WHERE sale_id=?",
                        payment.getValue(), saleStatus.getValue(), selectedSaleId[0]);
                executeUpdate("UPDATE invoices SET invoice_status=? WHERE sale_id=?",
                        invoiceStatusFromSaleStatus(saleStatus.getValue()), selectedSaleId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected sale updated successfully.");
                clearSalesForm(customerBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, payment, saleStatus);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedSaleId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a sale card first.");
                return;
            }
            try {
                executeUpdate("UPDATE sales SET sale_status='CANCELLED' WHERE sale_id=?", selectedSaleId[0]);
                executeUpdate("UPDATE invoices SET invoice_status='CANCELLED' WHERE sale_id=?", selectedSaleId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected sale was cancelled.");
                clearSalesForm(customerBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, payment, saleStatus);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Cancel Failed", friendlyDbMessage(ex));
            }
        });

        clear.setOnAction(e -> {
            clearSalesForm(customerBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, payment, saleStatus);
            selectedSaleId[0] = -1;
            if (selectedSaleCard[0] != null) {
                selectedSaleCard[0].setStyle((String) selectedSaleCard[0].getUserData());
                selectedSaleCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, saleCards);
        setCenterWithFixedRight(page, crudSidePanel("Sale Form", form, actions));
    }

    private void loadSaleCards(FlowPane container, String sql,
                               int[] selectedSaleId, VBox[] selectedSaleCard,
                               ComboBox<String> customerBox, ComboBox<String> pharmacistBox, ComboBox<String> branchBox,
                               ComboBox<String> payment, ComboBox<String> saleStatus,
                               Object... params) {
        container.getChildren().clear();
        selectedSaleId[0] = -1;
        selectedSaleCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String saleDate = safe(rs.getString("Sale_Date"));
                    String custId = safe(rs.getString("Customer_ID"));
                    String customer = safe(rs.getString("Customer"));
                    String pharmId = safe(rs.getString("Pharmacist_ID"));
                    String pharmacist = safe(rs.getString("Pharmacist"));
                    String brId = safe(rs.getString("Branch_ID"));
                    String branch = safe(rs.getString("Branch"));
                    String pay = safe(rs.getString("Payment"));
                    String total = safe(rs.getString("Total"));
                    String status = safe(rs.getString("Status"));

                    VBox card = saleCard(id, saleDate, custId, customer, pharmId, pharmacist, brId, branch, pay, total, status);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedSaleCard[0] != null) {
                            selectedSaleCard[0].setStyle((String) selectedSaleCard[0].getUserData());
                        }
                        selectedSaleId[0] = id;
                        selectedSaleCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        selectOptionById(customerBox, custId);
                        selectOptionById(pharmacistBox, pharmId);
                        selectOptionById(branchBox, brId);
                        payment.setValue(pay);
                        saleStatus.setValue(status);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No sales found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", friendlyDbMessage(e));
        }
    }

    private VBox saleCard(int id, String date, String customerId, String customer,
                          String pharmacistId, String pharmacist, String branchId, String branch,
                          String payment, String total, String status) {
        VBox card = baseInfoCard(300, 255);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Sale #" + id);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = coloredPill(status);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(title, spacer, pill);

        Label main = new Label(customer.isEmpty() ? "No customer" : "Customer: " + customer);
        main.setWrapText(true);
        main.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);
        details.add(cardField("Date", date), 0, 0);
        details.add(cardField("Total", total + " NIS"), 1, 0);
        details.add(cardField("Customer ID", customerId), 0, 1);
        details.add(cardField("Pharmacist", pharmacist), 1, 1);
        details.add(cardField("Branch", branch), 0, 2);
        details.add(cardField("Payment", payment), 1, 2);

        Label note = new Label("Click card to select for update/cancel");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, main, details, note);
        return card;
    }

    private String salesSelectSql() {
        return "SELECT s.sale_id AS ID, s.sale_date AS Sale_Date, " +
                "s.customer_id AS Customer_ID, CONCAT(c.first_name, ' ', c.last_name) AS Customer, " +
                "s.pharmacist_id AS Pharmacist_ID, CONCAT(p.first_name, ' ', p.last_name) AS Pharmacist, " +
                "s.branch_id AS Branch_ID, b.branch_name AS Branch, " +
                "s.payment_method AS Payment, s.total_amount AS Total, s.sale_status AS Status " +
                "FROM sales s " +
                "JOIN customers c ON s.customer_id = c.customer_id " +
                "JOIN pharmacists p ON s.pharmacist_id = p.pharmacist_id " +
                "JOIN branches b ON s.branch_id = b.branch_id";
    }

    private void reloadSaleDropdowns(ComboBox<String> customerBox, ComboBox<String> pharmacistBox, ComboBox<String> branchBox,
                                     ComboBox<String> warehouseBox, ComboBox<String> medicineBox) {
        loadReferenceOptions(customerBox,
                "SELECT customer_id, CONCAT(first_name, ' ', last_name) FROM customers ORDER BY first_name, last_name");
        loadReferenceOptions(pharmacistBox,
                "SELECT pharmacist_id, CONCAT(first_name, ' ', last_name) FROM pharmacists ORDER BY first_name, last_name");
        loadReferenceOptions(branchBox,
                "SELECT branch_id, branch_name FROM branches ORDER BY branch_name");
        loadReferenceOptions(warehouseBox,
                "SELECT warehouse_id, warehouse_name FROM warehouses ORDER BY warehouse_name");
        loadReferenceOptions(medicineBox,
                "SELECT medicine_id, medicine_name FROM medicines WHERE status='ACTIVE' ORDER BY medicine_name");
    }

    private void createSaleTransaction(int customerId, int pharmacistId, int branchId, String paymentMethod,
                                       int warehouseId, int medicineId, int quantity) throws SQLException {
        if (quantity <= 0) throw new SQLException("Quantity must be greater than zero.");

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            double unitPrice = getMedicinePrice(con, medicineId);
            int[] stock = getStockForUpdate(con, warehouseId, medicineId);
            int currentQty = stock[0];
            int reorderLevel = stock[1];

            if (currentQty < quantity) {
                throw new SQLException("Not enough stock. Available quantity = " + currentQty);
            }

            double total = unitPrice * quantity;

            int saleId;
            String saleSql = "INSERT INTO sales (customer_id, pharmacist_id, branch_id, payment_method, total_amount, sale_status) " +
                    "VALUES (?, ?, ?, ?, ?, 'COMPLETED')";
            try (PreparedStatement ps = con.prepareStatement(saleSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, customerId);
                ps.setInt(2, pharmacistId);
                ps.setInt(3, branchId);
                ps.setString(4, paymentMethod);
                ps.setDouble(5, total);
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) throw new SQLException("Failed to get generated sale ID.");
                    saleId = keys.getInt(1);
                }
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO sale_items (sale_id, medicine_id, quantity, unit_price) VALUES (?, ?, ?, ?)")) {
                ps.setInt(1, saleId);
                ps.setInt(2, medicineId);
                ps.setInt(3, quantity);
                ps.setDouble(4, unitPrice);
                ps.executeUpdate();
            }

            int newQty = currentQty - quantity;
            String newStatus = inventoryStatus(newQty, reorderLevel);
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE inventory SET quantity=?, status=? WHERE warehouse_id=? AND medicine_id=?")) {
                ps.setInt(1, newQty);
                ps.setString(2, newStatus);
                ps.setInt(3, warehouseId);
                ps.setInt(4, medicineId);
                ps.executeUpdate();
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO invoices (invoice_type, invoice_status, total_amount, sale_id, purchase_id) " +
                    "VALUES ('SALE', 'PAID', ?, ?, NULL)")) {
                ps.setDouble(1, total);
                ps.setInt(2, saleId);
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException ex) {
            if (con != null) con.rollback();
            throw ex;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    private void clearSalesForm(ComboBox<String> customerBox, ComboBox<String> pharmacistBox, ComboBox<String> branchBox,
                                ComboBox<String> warehouseBox, ComboBox<String> medicineBox, TextField quantity,
                                ComboBox<String> payment, ComboBox<String> status) {
        customerBox.getSelectionModel().clearSelection();
        pharmacistBox.getSelectionModel().clearSelection();
        branchBox.getSelectionModel().clearSelection();
        warehouseBox.getSelectionModel().clearSelection();
        medicineBox.getSelectionModel().clearSelection();
        quantity.clear();
        payment.setValue("CASH");
        status.setValue("COMPLETED");
    }

    private String invoiceStatusFromSaleStatus(String status) {
        if ("CANCELLED".equals(status)) return "CANCELLED";
        if ("REFUNDED".equals(status)) return "PARTIAL";
        return "PAID";
    }

    // =========================================================
    // PURCHASES PAGE
    // =========================================================
    private void showPurchases() {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Label title = new Label("Purchases Management");
        title.setStyle(TITLE);

        Label sub = new Label("Create purchases using a transaction. Choose supplier, pharmacist, branch, warehouse, and medicine from dropdowns.");
        sub.setStyle(SUBTITLE);

        HBox stats = new HBox(14);
        stats.getChildren().addAll(
                summaryCard("Total Purchases", one("SELECT COUNT(*) FROM purchases")),
                summaryCard("Received", one("SELECT COUNT(*) FROM purchases WHERE purchase_status='RECEIVED'")),
                summaryCard("Purchase Cost", one("SELECT COALESCE(SUM(total_cost), 0) FROM purchases WHERE purchase_status <> 'CANCELLED'")),
                summaryCard("Cancelled", one("SELECT COUNT(*) FROM purchases WHERE purchase_status='CANCELLED'"))
        );

        TextField search = new TextField();
        search.setPromptText("Filter Search: supplier / pharmacist / branch / status");
        search.setPrefWidth(500);

        Button searchBtn = new Button("Search");
        searchBtn.setStyle(BTN);

        Button refreshBtn = new Button("Refresh");
        refreshBtn.setStyle(GREEN);

        HBox top = new HBox(10, search, searchBtn, refreshBtn);
        top.setAlignment(Pos.CENTER_LEFT);

        FlowPane purchaseCards = new FlowPane(14, 14);
        purchaseCards.setPadding(new Insets(5, 0, 5, 0));
        purchaseCards.setPrefWrapLength(700);

        ComboBox<String> supplierBox = new ComboBox<>();
        supplierBox.setPromptText("Choose supplier");
        supplierBox.setPrefWidth(220);
        loadReferenceOptions(supplierBox,
                "SELECT supplier_id, supplier_name FROM suppliers ORDER BY supplier_name");

        ComboBox<String> pharmacistBox = new ComboBox<>();
        pharmacistBox.setPromptText("Choose pharmacist");
        pharmacistBox.setPrefWidth(220);
        loadReferenceOptions(pharmacistBox,
                "SELECT pharmacist_id, CONCAT(first_name, ' ', last_name) FROM pharmacists ORDER BY first_name, last_name");

        ComboBox<String> branchBox = new ComboBox<>();
        branchBox.setPromptText("Choose branch");
        branchBox.setPrefWidth(220);
        loadReferenceOptions(branchBox,
                "SELECT branch_id, branch_name FROM branches ORDER BY branch_name");

        ComboBox<String> warehouseBox = new ComboBox<>();
        warehouseBox.setPromptText("Choose warehouse");
        warehouseBox.setPrefWidth(220);
        loadReferenceOptions(warehouseBox,
                "SELECT warehouse_id, warehouse_name FROM warehouses ORDER BY warehouse_name");

        ComboBox<String> medicineBox = new ComboBox<>();
        medicineBox.setPromptText("Choose medicine");
        medicineBox.setPrefWidth(220);
        loadReferenceOptions(medicineBox,
                "SELECT medicine_id, medicine_name FROM medicines WHERE status='ACTIVE' ORDER BY medicine_name");

        TextField quantity = new TextField();
        quantity.setPromptText("Quantity");

        TextField costPrice = new TextField();
        costPrice.setPromptText("Cost Price");

        ComboBox<String> purchaseStatus = new ComboBox<>();
        purchaseStatus.getItems().addAll("PENDING", "RECEIVED", "CANCELLED");
        purchaseStatus.setValue("RECEIVED");
        purchaseStatus.setPrefWidth(220);

        GridPane form = formGrid();
        form.add(new Label("Supplier"), 0, 0);
        form.add(supplierBox, 1, 0);
        form.add(new Label("Pharmacist"), 0, 1);
        form.add(pharmacistBox, 1, 1);
        form.add(new Label("Branch"), 0, 2);
        form.add(branchBox, 1, 2);
        form.add(new Label("Warehouse"), 0, 3);
        form.add(warehouseBox, 1, 3);
        form.add(new Label("Medicine"), 0, 4);
        form.add(medicineBox, 1, 4);
        form.add(new Label("Quantity"), 0, 5);
        form.add(quantity, 1, 5);
        form.add(new Label("Cost Price"), 0, 6);
        form.add(costPrice, 1, 6);
        form.add(new Label("Purchase Status"), 0, 7);
        form.add(purchaseStatus, 1, 7);

        Button add = new Button("Add Purchase");
        add.setStyle(GREEN);
        Button update = new Button("Update Selected Status");
        update.setStyle(BTN);
        Button delete = new Button("Delete / Cancel Selected");
        delete.setStyle(RED);
        Button clear = new Button("Clear Form");
        clear.setStyle(GRAY);

        VBox actions = new VBox(10, add, update, delete, clear);
        actions.setAlignment(Pos.CENTER_LEFT);
        for (Node n : actions.getChildren()) {
            if (n instanceof Button) ((Button) n).setMaxWidth(Double.MAX_VALUE);
        }

        final int[] selectedPurchaseId = {-1};
        final VBox[] selectedPurchaseCard = {null};

        String selectSql = purchasesSelectSql();

        Runnable refresh = () -> loadPurchaseCards(
                purchaseCards,
                selectSql + " ORDER BY pu.purchase_date DESC",
                selectedPurchaseId,
                selectedPurchaseCard,
                supplierBox, pharmacistBox, branchBox, purchaseStatus
        );

        refresh.run();

        searchBtn.setOnAction(e -> {
            String k = "%" + search.getText().trim() + "%";
            loadPurchaseCards(
                    purchaseCards,
                    selectSql +
                            " WHERE su.supplier_name LIKE ? OR CONCAT(ph.first_name, ' ', ph.last_name) LIKE ? " +
                            "OR b.branch_name LIKE ? OR pu.purchase_status LIKE ? " +
                            "ORDER BY pu.purchase_date DESC",
                    selectedPurchaseId,
                    selectedPurchaseCard,
                    supplierBox, pharmacistBox, branchBox, purchaseStatus,
                    k, k, k, k
            );
        });

        refreshBtn.setOnAction(e -> {
            search.clear();
            reloadPurchaseDropdowns(supplierBox, pharmacistBox, branchBox, warehouseBox, medicineBox);
            clearPurchaseForm(supplierBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, costPrice, purchaseStatus);
            refresh.run();
        });

        add.setOnAction(e -> {
            try {
                createPurchaseTransaction(
                        selectedOptionId(supplierBox, "Supplier"),
                        selectedOptionId(pharmacistBox, "Pharmacist"),
                        selectedOptionId(branchBox, "Branch"),
                        selectedOptionId(warehouseBox, "Warehouse"),
                        selectedOptionId(medicineBox, "Medicine"),
                        Integer.parseInt(quantity.getText().trim()),
                        Double.parseDouble(costPrice.getText().trim())
                );
                alert(Alert.AlertType.INFORMATION, "Success", "Purchase created successfully with invoice and inventory update.");
                clearPurchaseForm(supplierBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, costPrice, purchaseStatus);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Add Purchase Failed", friendlyDbMessage(ex));
            }
        });

        update.setOnAction(e -> {
            if (selectedPurchaseId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a purchase card first.");
                return;
            }
            try {
                executeUpdate("UPDATE purchases SET purchase_status=? WHERE purchase_id=?",
                        purchaseStatus.getValue(), selectedPurchaseId[0]);
                executeUpdate("UPDATE invoices SET invoice_status=? WHERE purchase_id=?",
                        invoiceStatusFromPurchaseStatus(purchaseStatus.getValue()), selectedPurchaseId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected purchase updated successfully.");
                clearPurchaseForm(supplierBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, costPrice, purchaseStatus);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Update Failed", friendlyDbMessage(ex));
            }
        });

        delete.setOnAction(e -> {
            if (selectedPurchaseId[0] == -1) {
                alert(Alert.AlertType.WARNING, "No Selection", "Select a purchase card first.");
                return;
            }
            try {
                executeUpdate("UPDATE purchases SET purchase_status='CANCELLED' WHERE purchase_id=?", selectedPurchaseId[0]);
                executeUpdate("UPDATE invoices SET invoice_status='CANCELLED' WHERE purchase_id=?", selectedPurchaseId[0]);
                alert(Alert.AlertType.INFORMATION, "Success", "Selected purchase was cancelled.");
                clearPurchaseForm(supplierBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, costPrice, purchaseStatus);
                refresh.run();
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Cancel Failed", friendlyDbMessage(ex));
            }
        });

        clear.setOnAction(e -> {
            clearPurchaseForm(supplierBox, pharmacistBox, branchBox, warehouseBox, medicineBox, quantity, costPrice, purchaseStatus);
            selectedPurchaseId[0] = -1;
            if (selectedPurchaseCard[0] != null) {
                selectedPurchaseCard[0].setStyle((String) selectedPurchaseCard[0].getUserData());
                selectedPurchaseCard[0] = null;
            }
        });

        page.getChildren().addAll(title, sub, stats, top, purchaseCards);
        setCenterWithFixedRight(page, crudSidePanel("Purchase Form", form, actions));
    }

    private void loadPurchaseCards(FlowPane container, String sql,
                                   int[] selectedPurchaseId, VBox[] selectedPurchaseCard,
                                   ComboBox<String> supplierBox, ComboBox<String> pharmacistBox, ComboBox<String> branchBox,
                                   ComboBox<String> purchaseStatus,
                                   Object... params) {
        container.getChildren().clear();
        selectedPurchaseId[0] = -1;
        selectedPurchaseCard[0] = null;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                int count = 0;
                while (rs.next()) {
                    int id = rs.getInt("ID");
                    String purchaseDate = safe(rs.getString("Purchase_Date"));
                    String supId = safe(rs.getString("Supplier_ID"));
                    String supplier = safe(rs.getString("Supplier"));
                    String pharmId = safe(rs.getString("Pharmacist_ID"));
                    String pharmacist = safe(rs.getString("Pharmacist"));
                    String brId = safe(rs.getString("Branch_ID"));
                    String branch = safe(rs.getString("Branch"));
                    String total = safe(rs.getString("Total"));
                    String status = safe(rs.getString("Status"));

                    VBox card = purchaseCard(id, purchaseDate, supId, supplier, pharmId, pharmacist, brId, branch, total, status);
                    String baseStyle = (String) card.getUserData();

                    card.setOnMouseClicked(e -> {
                        if (selectedPurchaseCard[0] != null) {
                            selectedPurchaseCard[0].setStyle((String) selectedPurchaseCard[0].getUserData());
                        }
                        selectedPurchaseId[0] = id;
                        selectedPurchaseCard[0] = card;
                        card.setStyle(baseStyle + "-fx-border-color: #2563eb; -fx-border-width: 2;");

                        selectOptionById(supplierBox, supId);
                        selectOptionById(pharmacistBox, pharmId);
                        selectOptionById(branchBox, brId);
                        purchaseStatus.setValue(status);
                    });

                    container.getChildren().add(card);
                    count++;
                }

                if (count == 0) {
                    container.getChildren().add(emptyCardsMessage("No purchases found."));
                }
            }

        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", friendlyDbMessage(e));
        }
    }

    private VBox purchaseCard(int id, String date, String supplierId, String supplier,
                              String pharmacistId, String pharmacist, String branchId, String branch,
                              String total, String status) {
        VBox card = baseInfoCard(300, 250);

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);

        Label title = new Label("Purchase #" + id);
        title.setStyle("-fx-font-size: 17px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label pill = coloredPill(status);

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        header.getChildren().addAll(title, spacer, pill);

        Label main = new Label(supplier.isEmpty() ? "No supplier" : "Supplier: " + supplier);
        main.setWrapText(true);
        main.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(7);
        details.add(cardField("Date", date), 0, 0);
        details.add(cardField("Total", total + " NIS"), 1, 0);
        details.add(cardField("Supplier ID", supplierId), 0, 1);
        details.add(cardField("Pharmacist", pharmacist), 1, 1);
        details.add(cardField("Branch", branch), 0, 2);
        details.add(cardField("Branch ID", branchId), 1, 2);

        Label note = new Label("Click card to select for update/cancel");
        note.setStyle("-fx-font-size: 11px; -fx-text-fill: #94a3b8;");

        card.getChildren().addAll(header, main, details, note);
        return card;
    }

    private String purchasesSelectSql() {
        return "SELECT pu.purchase_id AS ID, pu.purchase_date AS Purchase_Date, " +
                "pu.supplier_id AS Supplier_ID, su.supplier_name AS Supplier, " +
                "pu.pharmacist_id AS Pharmacist_ID, CONCAT(ph.first_name, ' ', ph.last_name) AS Pharmacist, " +
                "pu.branch_id AS Branch_ID, b.branch_name AS Branch, " +
                "pu.total_cost AS Total, pu.purchase_status AS Status " +
                "FROM purchases pu " +
                "JOIN suppliers su ON pu.supplier_id = su.supplier_id " +
                "JOIN pharmacists ph ON pu.pharmacist_id = ph.pharmacist_id " +
                "JOIN branches b ON pu.branch_id = b.branch_id";
    }

    private void reloadPurchaseDropdowns(ComboBox<String> supplierBox, ComboBox<String> pharmacistBox, ComboBox<String> branchBox,
                                         ComboBox<String> warehouseBox, ComboBox<String> medicineBox) {
        loadReferenceOptions(supplierBox,
                "SELECT supplier_id, supplier_name FROM suppliers ORDER BY supplier_name");
        loadReferenceOptions(pharmacistBox,
                "SELECT pharmacist_id, CONCAT(first_name, ' ', last_name) FROM pharmacists ORDER BY first_name, last_name");
        loadReferenceOptions(branchBox,
                "SELECT branch_id, branch_name FROM branches ORDER BY branch_name");
        loadReferenceOptions(warehouseBox,
                "SELECT warehouse_id, warehouse_name FROM warehouses ORDER BY warehouse_name");
        loadReferenceOptions(medicineBox,
                "SELECT medicine_id, medicine_name FROM medicines WHERE status='ACTIVE' ORDER BY medicine_name");
    }

    private void createPurchaseTransaction(int supplierId, int pharmacistId, int branchId,
                                           int warehouseId, int medicineId, int quantity,
                                           double costPrice) throws SQLException {
        if (quantity <= 0) throw new SQLException("Quantity must be greater than zero.");
        if (costPrice < 0) throw new SQLException("Cost price cannot be negative.");

        Connection con = null;
        try {
            con = DBConnection.getConnection();
            con.setAutoCommit(false);

            double total = quantity * costPrice;
            int purchaseId;

            String purchaseSql = "INSERT INTO purchases (supplier_id, pharmacist_id, branch_id, total_cost, purchase_status) " +
                    "VALUES (?, ?, ?, ?, 'RECEIVED')";
            try (PreparedStatement ps = con.prepareStatement(purchaseSql, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, supplierId);
                ps.setInt(2, pharmacistId);
                ps.setInt(3, branchId);
                ps.setDouble(4, total);
                ps.executeUpdate();

                try (ResultSet keys = ps.getGeneratedKeys()) {
                    if (!keys.next()) throw new SQLException("Failed to get generated purchase ID.");
                    purchaseId = keys.getInt(1);
                }
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO purchase_items (purchase_id, medicine_id, quantity, cost_price) VALUES (?, ?, ?, ?)")) {
                ps.setInt(1, purchaseId);
                ps.setInt(2, medicineId);
                ps.setInt(3, quantity);
                ps.setDouble(4, costPrice);
                ps.executeUpdate();
            }

            int reorderLevel = 10;
            int currentQty = 0;
            boolean exists = false;

            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT quantity, reorder_level FROM inventory WHERE warehouse_id=? AND medicine_id=? FOR UPDATE")) {
                ps.setInt(1, warehouseId);
                ps.setInt(2, medicineId);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        exists = true;
                        currentQty = rs.getInt("quantity");
                        reorderLevel = rs.getInt("reorder_level");
                    }
                }
            }

            int newQty = currentQty + quantity;
            String status = inventoryStatus(newQty, reorderLevel);

            if (exists) {
                try (PreparedStatement ps = con.prepareStatement(
                        "UPDATE inventory SET quantity=?, status=? WHERE warehouse_id=? AND medicine_id=?")) {
                    ps.setInt(1, newQty);
                    ps.setString(2, status);
                    ps.setInt(3, warehouseId);
                    ps.setInt(4, medicineId);
                    ps.executeUpdate();
                }
            } else {
                try (PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO inventory (warehouse_id, medicine_id, quantity, reorder_level, status) VALUES (?, ?, ?, ?, ?)")) {
                    ps.setInt(1, warehouseId);
                    ps.setInt(2, medicineId);
                    ps.setInt(3, newQty);
                    ps.setInt(4, reorderLevel);
                    ps.setString(5, status);
                    ps.executeUpdate();
                }
            }

            try (PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO invoices (invoice_type, invoice_status, total_amount, sale_id, purchase_id) " +
                    "VALUES ('PURCHASE', 'PAID', ?, NULL, ?)")) {
                ps.setDouble(1, total);
                ps.setInt(2, purchaseId);
                ps.executeUpdate();
            }

            con.commit();
        } catch (SQLException ex) {
            if (con != null) con.rollback();
            throw ex;
        } finally {
            if (con != null) {
                con.setAutoCommit(true);
                con.close();
            }
        }
    }

    private void clearPurchaseForm(ComboBox<String> supplierBox, ComboBox<String> pharmacistBox, ComboBox<String> branchBox,
                                   ComboBox<String> warehouseBox, ComboBox<String> medicineBox, TextField quantity,
                                   TextField costPrice, ComboBox<String> status) {
        supplierBox.getSelectionModel().clearSelection();
        pharmacistBox.getSelectionModel().clearSelection();
        branchBox.getSelectionModel().clearSelection();
        warehouseBox.getSelectionModel().clearSelection();
        medicineBox.getSelectionModel().clearSelection();
        quantity.clear();
        costPrice.clear();
        status.setValue("RECEIVED");
    }

    private String invoiceStatusFromPurchaseStatus(String status) {
        if ("CANCELLED".equals(status)) return "CANCELLED";
        if ("PENDING".equals(status)) return "UNPAID";
        return "PAID";
    }

    // =========================================================
    // REPORTS PAGE
    // =========================================================

    // Simple enum so each report can declare exactly one fixed chart type.
    private enum ChartKind { LINE, BAR, PIE }

    private void showReports() {
        VBox page = new VBox(18);
        page.setPadding(new Insets(26));

        Label title = new Label("Reports / Statistics");
        title.setStyle(TITLE);

        Label sub = new Label("All required SQL reports are available here. Existing reports are kept, and missing assignment queries were added.");
        sub.setStyle(SUBTITLE);

        HBox reportStats = new HBox(14);
        reportStats.getChildren().addAll(
                summaryCard("Sales Revenue", one("SELECT COALESCE(SUM(total_amount), 0) FROM sales WHERE sale_status <> 'CANCELLED'")),
                summaryCard("Purchase Cost", one("SELECT COALESCE(SUM(total_cost), 0) FROM purchases WHERE purchase_status <> 'CANCELLED'")),
                summaryCard("Inventory Value", one("SELECT COALESCE(SUM(i.quantity * m.selling_price), 0) FROM inventory i JOIN medicines m ON i.medicine_id = m.medicine_id")),
                summaryCard("Available Reports", "25")
        );

        FlowPane buttons = new FlowPane(14, 14);

        buttons.getChildren().add(reportMenuCard("1. All Medicines Details",
                "Medicines with category, supplier, selling price, and expiry date",
                () -> openReportScreen(
                        "1. All Medicines Details",
                        "Retrieve all medicines with their category, supplier, selling price, and expiration date.",
                        "SELECT m.medicine_id AS ID, m.medicine_name AS Medicine, " +
                                "c.category_name AS Category, s.supplier_name AS Supplier, " +
                                "m.selling_price AS Price, m.expiry_date AS Expiry_Date, m.status AS Status " +
                                "FROM medicines m " +
                                "JOIN categories c ON m.category_id = c.category_id " +
                                "JOIN suppliers s ON m.supplier_id = s.supplier_id " +
                                "ORDER BY m.medicine_name",
                        "Medicine", "Price", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("2. Expired / Expiring Soon Medicines",
                "Expired medicines or medicines expiring in the next 30 days",
                () -> openReportScreen(
                        "2. Expired / Expiring Soon Medicines",
                        "Find all medicines that are expired or will expire within the next 30 days.",
                        "SELECT m.medicine_name AS Medicine, c.category_name AS Category, " +
                                "s.supplier_name AS Supplier, m.expiry_date AS Expiry_Date, " +
                                "DATEDIFF(m.expiry_date, CURDATE()) AS Days_Left " +
                                "FROM medicines m " +
                                "JOIN categories c ON m.category_id = c.category_id " +
                                "JOIN suppliers s ON m.supplier_id = s.supplier_id " +
                                "WHERE m.expiry_date < CURDATE() OR m.expiry_date <= DATE_ADD(CURDATE(), INTERVAL 30 DAY) " +
                                "ORDER BY m.expiry_date",
                        "Medicine", "Days_Left", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("3. Medicines Below Reorder Level",
                "Total quantity across all warehouses is below reorder level",
                () -> openReportScreen(
                        "3. Medicines Below Reorder Level",
                        "Retrieve all medicines whose total quantity across all warehouses is below the reorder level.",
                        "SELECT m.medicine_name AS Medicine, " +
                                "COALESCE(SUM(i.quantity), 0) AS Total_Quantity, " +
                                "COALESCE(SUM(i.reorder_level), 0) AS Total_Reorder_Level " +
                                "FROM medicines m " +
                                "JOIN inventory i ON m.medicine_id = i.medicine_id " +
                                "GROUP BY m.medicine_id, m.medicine_name " +
                                "HAVING COALESCE(SUM(i.quantity), 0) < COALESCE(SUM(i.reorder_level), 0) " +
                                "ORDER BY Total_Quantity ASC",
                        "Medicine", "Total_Quantity", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("4. Total Quantity By Medicine",
                "Total available quantity of each medicine across all warehouses",
                () -> openReportScreen(
                        "4. Total Quantity By Medicine",
                        "Compute the total available quantity of each medicine across all warehouses.",
                        "SELECT m.medicine_name AS Medicine, COALESCE(SUM(i.quantity), 0) AS Total_Quantity " +
                                "FROM medicines m " +
                                "LEFT JOIN inventory i ON m.medicine_id = i.medicine_id " +
                                "GROUP BY m.medicine_id, m.medicine_name " +
                                "ORDER BY Total_Quantity DESC, m.medicine_name",
                        "Medicine", "Total_Quantity", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("5. Medicine Quantity By Warehouse",
                "Quantity of each medicine stored in each warehouse",
                () -> openReportScreen(
                        "5. Medicine Quantity By Warehouse",
                        "Retrieve the quantity of each medicine stored in each warehouse.",
                        "SELECT CONCAT(w.warehouse_name, ' / ', m.medicine_name) AS Warehouse_Medicine, " +
                                "w.warehouse_name AS Warehouse, m.medicine_name AS Medicine, " +
                                "i.quantity AS Quantity, i.status AS Status " +
                                "FROM inventory i " +
                                "JOIN warehouses w ON i.warehouse_id = w.warehouse_id " +
                                "JOIN medicines m ON i.medicine_id = m.medicine_id " +
                                "ORDER BY w.warehouse_name, m.medicine_name",
                        "Warehouse_Medicine", "Quantity", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("6. Warehouse Highest Stock Units",
                "Warehouse that stores the highest total medicine units",
                () -> openReportScreen(
                        "6. Warehouse Highest Stock Units",
                        "Find the warehouse that stores the highest total number of medicine units.",
                        "SELECT w.warehouse_name AS Warehouse, COALESCE(SUM(i.quantity), 0) AS Total_Units " +
                                "FROM warehouses w " +
                                "JOIN inventory i ON w.warehouse_id = i.warehouse_id " +
                                "GROUP BY w.warehouse_id, w.warehouse_name " +
                                "ORDER BY Total_Units DESC " +
                                "LIMIT 1",
                        "Warehouse", "Total_Units", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("7. Medicines By Selected Supplier",
                "Enter supplier ID and list its medicines sorted by medicine name",
                () -> openSupplierReportScreen(
                        "7. Medicines By Selected Supplier",
                        "Retrieve all medicines supplied by a specific supplier, sorted by medicine name.",
                        "SELECT s.supplier_name AS Supplier, m.medicine_name AS Medicine, " +
                                "c.category_name AS Category, m.selling_price AS Price, m.expiry_date AS Expiry_Date " +
                                "FROM medicines m " +
                                "JOIN suppliers s ON m.supplier_id = s.supplier_id " +
                                "JOIN categories c ON m.category_id = c.category_id " +
                                "WHERE m.supplier_id = ? " +
                                "ORDER BY m.medicine_name",
                        "Medicine", "Price", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("8. Suppliers With Medicine Count",
                "Suppliers and total number of medicines they supply",
                () -> openReportScreen(
                        "8. Suppliers With Medicine Count",
                        "List suppliers with the total number of medicines they supply.",
                        "SELECT s.supplier_name AS Supplier, COUNT(m.medicine_id) AS Number_Of_Medicines " +
                                "FROM suppliers s " +
                                "LEFT JOIN medicines m ON s.supplier_id = m.supplier_id " +
                                "GROUP BY s.supplier_id, s.supplier_name " +
                                "ORDER BY Number_Of_Medicines DESC, s.supplier_name",
                        "Supplier", "Number_Of_Medicines", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("9. Sales Between Dates",
                "Sales transactions within a date range",
                () -> openDateReportScreen(
                        "9. Sales Between Dates",
                        "Retrieve all sales transactions within a specific date range, including customer name, pharmacist name, payment method, and total amount.",
                        (s, en) -> "SELECT s.sale_id AS Sale_ID, s.sale_date AS Sale_Date, " +
                                "CONCAT(c.first_name, ' ', c.last_name) AS Customer, " +
                                "CONCAT(p.first_name, ' ', p.last_name) AS Pharmacist, " +
                                "b.branch_name AS Branch, s.payment_method AS Payment, " +
                                "s.total_amount AS Total, s.sale_status AS Status " +
                                "FROM sales s " +
                                "JOIN customers c ON s.customer_id = c.customer_id " +
                                "JOIN pharmacists p ON s.pharmacist_id = p.pharmacist_id " +
                                "JOIN branches b ON s.branch_id = b.branch_id " +
                                "WHERE s.sale_date BETWEEN ? AND ? " +
                                "ORDER BY s.sale_date",
                        "Sale_Date", "Total", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("10. Sales With Medicine Details",
                "All sales transactions and their sold medicine details",
                () -> openReportScreen(
                        "10. Sales With Medicine Details",
                        "Retrieve all sales transactions and their sold medicine details.",
                        "SELECT CONCAT('Sale ', s.sale_id, ' / ', m.medicine_name) AS Sale_Medicine, " +
                                "s.sale_id AS Sale_ID, s.sale_date AS Sale_Date, " +
                                "CONCAT(c.first_name, ' ', c.last_name) AS Customer, " +
                                "m.medicine_name AS Medicine, si.quantity AS Quantity, " +
                                "si.unit_price AS Unit_Price, si.line_total AS Line_Total " +
                                "FROM sales s " +
                                "JOIN customers c ON s.customer_id = c.customer_id " +
                                "JOIN sale_items si ON s.sale_id = si.sale_id " +
                                "JOIN medicines m ON si.medicine_id = m.medicine_id " +
                                "ORDER BY s.sale_date DESC, s.sale_id",
                        "Sale_Medicine", "Line_Total", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("11. Daily Sales Amount Within Period",
                "Total sales amount for each day in selected period",
                () -> openDateReportScreen(
                        "11. Daily Sales Amount Within Period",
                        "Compute the total sales amount for each day within a specific period.",
                        (s, en) -> "SELECT DATE(s.sale_date) AS Sale_Day, " +
                                "COALESCE(SUM(s.total_amount), 0) AS Daily_Total " +
                                "FROM sales s " +
                                "WHERE s.sale_status <> 'CANCELLED' AND s.sale_date BETWEEN ? AND ? " +
                                "GROUP BY DATE(s.sale_date) " +
                                "ORDER BY DATE(s.sale_date)",
                        "Sale_Day", "Daily_Total", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("12. Profit Within Date Range",
                "Profit based on sale prices and average purchase costs",
                () -> openDateReportScreen(
                        "12. Profit Within Date Range",
                        "Compute the total profit generated within a specific date range based on sale prices and purchase costs.",
                        (s, en) -> "SELECT DATE(sa.sale_date) AS Sale_Day, " +
                                "SUM(si.quantity * (si.unit_price - COALESCE(pc.avg_cost, 0))) AS Total_Profit " +
                                "FROM sales sa " +
                                "JOIN sale_items si ON sa.sale_id = si.sale_id " +
                                "LEFT JOIN (SELECT medicine_id, AVG(cost_price) AS avg_cost FROM purchase_items GROUP BY medicine_id) pc " +
                                "ON si.medicine_id = pc.medicine_id " +
                                "WHERE sa.sale_status <> 'CANCELLED' AND sa.sale_date BETWEEN ? AND ? " +
                                "GROUP BY DATE(sa.sale_date) " +
                                "ORDER BY DATE(sa.sale_date)",
                        "Sale_Day", "Total_Profit", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("13. Top 5 Most Sold Medicines",
                "Top 5 medicines by total quantity sold",
                () -> openReportScreen(
                        "13. Top 5 Most Sold Medicines",
                        "Retrieve the top 5 most sold medicines based on total quantity sold.",
                        "SELECT m.medicine_name AS Medicine, SUM(si.quantity) AS Total_Quantity_Sold, " +
                                "SUM(si.line_total) AS Total_Revenue " +
                                "FROM sale_items si " +
                                "JOIN sales s ON si.sale_id = s.sale_id " +
                                "JOIN medicines m ON si.medicine_id = m.medicine_id " +
                                "WHERE s.sale_status <> 'CANCELLED' " +
                                "GROUP BY m.medicine_id, m.medicine_name " +
                                "HAVING SUM(si.quantity) > 0 " +
                                "ORDER BY Total_Quantity_Sold DESC " +
                                "LIMIT 5",
                        "Medicine", "Total_Quantity_Sold", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("14. Least Sold Medicines",
                "Slow-moving products by lowest sold quantity",
                () -> openReportScreen(
                        "14. Least Sold Medicines",
                        "Retrieve the least sold medicines to identify slow-moving products.",
                        "SELECT m.medicine_name AS Medicine, COALESCE(SUM(si.quantity), 0) AS Total_Sold " +
                                "FROM medicines m " +
                                "LEFT JOIN sale_items si ON m.medicine_id = si.medicine_id " +
                                "LEFT JOIN sales s ON si.sale_id = s.sale_id " +
                                "WHERE s.sale_status <> 'CANCELLED' OR s.sale_id IS NULL " +
                                "GROUP BY m.medicine_id, m.medicine_name " +
                                "ORDER BY Total_Sold ASC, m.medicine_name " +
                                "LIMIT 10",
                        "Medicine", "Total_Sold", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("15. Top Customers By Purchase Amount",
                "Customers with the highest total purchase amount",
                () -> openReportScreen(
                        "15. Top Customers By Purchase Amount",
                        "Find customers with the highest total purchase amount.",
                        "SELECT CONCAT(c.first_name, ' ', c.last_name) AS Customer, " +
                                "COUNT(s.sale_id) AS Number_Of_Sales, " +
                                "COALESCE(SUM(s.total_amount), 0) AS Total_Spent " +
                                "FROM customers c " +
                                "JOIN sales s ON c.customer_id = s.customer_id " +
                                "WHERE s.sale_status <> 'CANCELLED' " +
                                "GROUP BY c.customer_id, c.first_name, c.last_name " +
                                "ORDER BY Total_Spent DESC",
                        "Customer", "Total_Spent", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("16. Sales Count By Pharmacist Within Period",
                "Number of sales processed by each pharmacist in selected period",
                () -> openDateReportScreen(
                        "16. Sales Count By Pharmacist Within Period",
                        "List the number of sales processed by each pharmacist within a given period.",
                        (s, en) -> "SELECT CONCAT(p.first_name, ' ', p.last_name) AS Pharmacist, " +
                                "COUNT(s.sale_id) AS Number_Of_Sales " +
                                "FROM pharmacists p " +
                                "JOIN sales s ON p.pharmacist_id = s.pharmacist_id " +
                                "WHERE s.sale_status <> 'CANCELLED' AND s.sale_date BETWEEN ? AND ? " +
                                "GROUP BY p.pharmacist_id, p.first_name, p.last_name " +
                                "ORDER BY Number_Of_Sales DESC",
                        "Pharmacist", "Number_Of_Sales", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("17. Purchases From Supplier Within Date Range",
                "Enter supplier ID and period to show purchases",
                () -> openSupplierDateReportScreen(
                        "17. Purchases From Supplier Within Date Range",
                        "Retrieve all purchase transactions from a specific supplier within a given date range.",
                        "SELECT pu.purchase_id AS Purchase_ID, pu.purchase_date AS Purchase_Date, " +
                                "su.supplier_name AS Supplier, CONCAT(ph.first_name, ' ', ph.last_name) AS Pharmacist, " +
                                "b.branch_name AS Branch, pu.total_cost AS Total_Cost, pu.purchase_status AS Status " +
                                "FROM purchases pu " +
                                "JOIN suppliers su ON pu.supplier_id = su.supplier_id " +
                                "JOIN pharmacists ph ON pu.pharmacist_id = ph.pharmacist_id " +
                                "JOIN branches b ON pu.branch_id = b.branch_id " +
                                "WHERE pu.supplier_id = ? AND pu.purchase_date BETWEEN ? AND ? " +
                                "ORDER BY pu.purchase_date",
                        "Purchase_Date", "Total_Cost", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("18. Total Purchase Cost By Supplier",
                "Total purchase cost for each supplier",
                () -> openReportScreen(
                        "18. Total Purchase Cost By Supplier",
                        "Compute the total purchase cost for each supplier.",
                        "SELECT su.supplier_name AS Supplier, COUNT(pu.purchase_id) AS Number_Of_Purchases, " +
                                "COALESCE(SUM(pu.total_cost), 0) AS Total_Purchases " +
                                "FROM suppliers su " +
                                "LEFT JOIN purchases pu ON su.supplier_id = pu.supplier_id AND pu.purchase_status <> 'CANCELLED' " +
                                "GROUP BY su.supplier_id, su.supplier_name " +
                                "ORDER BY Total_Purchases DESC",
                        "Supplier", "Total_Purchases", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("19. Most Used Payment Method",
                "Payment method with highest number of sales",
                () -> openReportScreen(
                        "19. Most Used Payment Method",
                        "Find the most commonly used payment method in sales transactions.",
                        "SELECT payment_method AS Payment_Method, COUNT(sale_id) AS Number_Of_Sales " +
                                "FROM sales " +
                                "WHERE sale_status <> 'CANCELLED' " +
                                "GROUP BY payment_method " +
                                "ORDER BY Number_Of_Sales DESC " +
                                "LIMIT 1",
                        "Payment_Method", "Number_Of_Sales", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("20. Category Sales Ranking",
                "Medicine categories ranked by total sales amount",
                () -> openReportScreen(
                        "20. Category Sales Ranking",
                        "Retrieve medicine categories ranked by total sales amount.",
                        "SELECT c.category_name AS Category, SUM(si.quantity) AS Total_Quantity_Sold, " +
                                "SUM(si.line_total) AS Total_Revenue " +
                                "FROM sale_items si " +
                                "JOIN sales s ON si.sale_id = s.sale_id " +
                                "JOIN medicines m ON si.medicine_id = m.medicine_id " +
                                "JOIN categories c ON m.category_id = c.category_id " +
                                "WHERE s.sale_status <> 'CANCELLED' " +
                                "GROUP BY c.category_id, c.category_name " +
                                "ORDER BY Total_Revenue DESC",
                        "Category", "Total_Revenue", ChartKind.BAR, false
                )));

        // Extra old reports kept for safety; they do not replace the 20 required reports above.
        buttons.getChildren().add(reportMenuCard("Extra. Purchases Between Dates",
                "Old report kept: purchases filtered by date range",
                () -> openDateReportScreen(
                        "Extra. Purchases Between Dates",
                        "Purchases filtered by a start and end date.",
                        (s, en) -> "SELECT pu.purchase_id AS Purchase_ID, pu.purchase_date AS Purchase_Date, " +
                                "su.supplier_name AS Supplier, CONCAT(ph.first_name, ' ', ph.last_name) AS Pharmacist, " +
                                "b.branch_name AS Branch, pu.total_cost AS Total_Cost, pu.purchase_status AS Status " +
                                "FROM purchases pu " +
                                "JOIN suppliers su ON pu.supplier_id = su.supplier_id " +
                                "JOIN pharmacists ph ON pu.pharmacist_id = ph.pharmacist_id " +
                                "JOIN branches b ON pu.branch_id = b.branch_id " +
                                "WHERE pu.purchase_date BETWEEN ? AND ? " +
                                "ORDER BY pu.purchase_date",
                        "Purchase_Date", "Total_Cost", ChartKind.BAR
                )));

        buttons.getChildren().add(reportMenuCard("Extra. Sales By Branch",
                "Old report kept: number of sales and total revenue per branch",
                () -> openReportScreen(
                        "Extra. Sales By Branch",
                        "Number of sales and total revenue per branch.",
                        "SELECT b.branch_name AS Branch, COUNT(s.sale_id) AS Number_Of_Sales, " +
                                "COALESCE(SUM(s.total_amount), 0) AS Total_Sales " +
                                "FROM branches b " +
                                "LEFT JOIN sales s ON b.branch_id = s.branch_id AND s.sale_status <> 'CANCELLED' " +
                                "GROUP BY b.branch_id, b.branch_name " +
                                "ORDER BY Total_Sales DESC",
                        "Branch", "Total_Sales", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("Extra. Inventory Value By Warehouse",
                "Old report kept: stock units and inventory value per warehouse",
                () -> openReportScreen(
                        "Extra. Inventory Value By Warehouse",
                        "Total stock units and value per warehouse.",
                        "SELECT w.warehouse_name AS Warehouse, SUM(i.quantity) AS Total_Units, " +
                                "SUM(i.quantity * m.selling_price) AS Inventory_Value " +
                                "FROM inventory i " +
                                "JOIN warehouses w ON i.warehouse_id = w.warehouse_id " +
                                "JOIN medicines m ON i.medicine_id = m.medicine_id " +
                                "GROUP BY w.warehouse_id, w.warehouse_name " +
                                "ORDER BY Inventory_Value DESC",
                        "Warehouse", "Inventory_Value", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("Extra. Payment Method Statistics",
                "Old report kept: sales count and total amount by payment method",
                () -> openReportScreen(
                        "Extra. Payment Method Statistics",
                        "Revenue distribution and sales count by payment method.",
                        "SELECT payment_method AS Payment_Method, COUNT(sale_id) AS Number_Of_Sales, " +
                                "COALESCE(SUM(total_amount), 0) AS Total_Amount " +
                                "FROM sales " +
                                "WHERE sale_status <> 'CANCELLED' " +
                                "GROUP BY payment_method " +
                                "ORDER BY Total_Amount DESC",
                        "Payment_Method", "Total_Amount", ChartKind.BAR, false
                )));

        buttons.getChildren().add(reportMenuCard("Extra. Expired Medicines by Category",
                "Old report kept: expired medicines grouped by category",
                () -> openReportScreen(
                        "Extra. Expired Medicines by Category",
                        "Expired medicines grouped by category.",
                        "SELECT c.category_name AS Category, COUNT(m.medicine_id) AS Expired_Medicines " +
                                "FROM categories c " +
                                "LEFT JOIN medicines m ON c.category_id = m.category_id AND m.expiry_date < CURDATE() " +
                                "GROUP BY c.category_id, c.category_name " +
                                "HAVING Expired_Medicines > 0 " +
                                "ORDER BY Expired_Medicines DESC",
                        "Category", "Expired_Medicines", ChartKind.BAR, false
                )));

        page.getChildren().addAll(title, sub, reportStats, buttons);
        setCenter(page);
    }

    /** A clickable card on the Reports menu, similar to a dashboard tile. */
    private VBox reportMenuCard(String name, String description, Runnable onOpen) {
        VBox card = new VBox(8);
        card.setPrefWidth(260);
        card.setMinHeight(110);
        card.setAlignment(Pos.CENTER_LEFT);
        card.setOnMouseClicked(e -> onOpen.run());
        card.setStyle(CARD + "-fx-cursor: hand;");

        card.setOnMouseEntered(e -> card.setStyle(CARD +
                "-fx-cursor: hand;" +
                "-fx-effect: dropshadow(gaussian, rgba(37,99,235,0.35), 14, 0, 0, 4);"));
        card.setOnMouseExited(e -> card.setStyle(CARD + "-fx-cursor: hand;"));

        Label n = new Label(name);
        n.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");
        n.setWrapText(true);

        Label d = new Label(description);
        d.setStyle(SUBTITLE);
        d.setWrapText(true);

        card.getChildren().addAll(n, d);
        return card;
    }

    /**
     * Opens a dedicated full-screen report view: big title, subtitle, Back button,
     * the data table, and exactly one large chart of the fixed chartKind.
     * If needsDateRange is true this is only used internally by openDateReportScreen.
     */
    private void openReportScreen(String reportTitle, String description, String sql,
                                   String labelColumn, String valueColumn,
                                   ChartKind chartKind, boolean needsDateRange) {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Button back = new Button("\u2190 Back to Reports");
        back.setStyle(GRAY);
        back.setOnAction(e -> showReports());

        Label title = new Label(reportTitle);
        title.setStyle(TITLE);

        Label sub = new Label(description);
        sub.setStyle(SUBTITLE);

        TableView<ObservableList<String>> table = new TableView<>();
        table.setPrefHeight(280);
        table.setMinHeight(240);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        loadTable(table, sql);

        Node chart = buildReportChart(table, labelColumn, valueColumn, chartKind);

        VBox chartCard = new VBox(10);
        chartCard.setStyle(CARD);
        chartCard.setPadding(new Insets(18));

        Label chartTitle = new Label(reportTitle + " Bar Chart");
        chartTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        chartCard.getChildren().addAll(chartTitle, chart);
        VBox.setVgrow(chart, Priority.ALWAYS);

        VBox.setVgrow(chartCard, Priority.ALWAYS);
        VBox.setVgrow(table, Priority.NEVER);

        page.getChildren().addAll(back, title, sub, table, chartCard);
        setCenter(page);
    }

    /** Functional interface so date-range reports can build their SQL once dates are picked. */
    private interface DateRangeSql {
        String build(Date start, Date end);
    }

    private void loadSupplierChoices(ComboBox<String> combo) {
        combo.getItems().clear();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT supplier_id, supplier_name FROM suppliers ORDER BY supplier_name");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                combo.getItems().add(rs.getInt("supplier_id") + " - " + rs.getString("supplier_name"));
            }

        } catch (Exception e) {
            System.out.println("Supplier choices error: " + e.getMessage());
        }
    }

    private void openSupplierReportScreen(String reportTitle, String description, String sql,
                                          String labelColumn, String valueColumn, ChartKind chartKind) {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Button back = new Button("\u2190 Back to Reports");
        back.setStyle(GRAY);
        back.setOnAction(e -> showReports());

        Label title = new Label(reportTitle);
        title.setStyle(TITLE);

        Label sub = new Label(description);
        sub.setStyle(SUBTITLE);

        Label supplierTitle = new Label("Available Suppliers - choose one or type its ID");
        supplierTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        TableView<ObservableList<String>> suppliersTable = new TableView<>();
        suppliersTable.setPrefHeight(150);
        suppliersTable.setMinHeight(120);
        suppliersTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        loadTable(suppliersTable,
                "SELECT supplier_id AS Supplier_ID, supplier_name AS Supplier, company_name AS Company, phone_number AS Phone " +
                "FROM suppliers ORDER BY supplier_name");

        ComboBox<String> supplierChoice = new ComboBox<>();
        supplierChoice.setPromptText("Choose supplier");
        supplierChoice.setPrefWidth(300);
        loadSupplierChoices(supplierChoice);

        TextField supplierId = new TextField();
        supplierId.setPromptText("Supplier ID");
        supplierId.setPrefWidth(160);

        supplierChoice.setOnAction(e -> {
            String selected = supplierChoice.getValue();
            if (selected != null && selected.contains(" - ")) {
                supplierId.setText(selected.substring(0, selected.indexOf(" - ")).trim());
            }
        });

        suppliersTable.setOnMouseClicked(e -> {
            ObservableList<String> selected = suppliersTable.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.isEmpty()) {
                supplierId.setText(selected.get(0));
            }
        });

        Button run = new Button("Run Report");
        run.setStyle(BTN);

        HBox inputs = new HBox(10, supplierChoice, supplierId, run);
        inputs.setAlignment(Pos.CENTER_LEFT);

        TableView<ObservableList<String>> table = new TableView<>();
        table.setPrefHeight(280);
        table.setMinHeight(240);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        VBox chartCard = new VBox(10);
        chartCard.setStyle(CARD);
        chartCard.setPadding(new Insets(18));

        Label chartTitle = new Label(reportTitle + " Bar Chart");
        chartTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label placeholder = new Label("Choose a supplier or type Supplier ID, then press Run Report.");
        placeholder.setStyle(SUBTITLE);
        chartCard.getChildren().addAll(chartTitle, placeholder);

        run.setOnAction(e -> {
            try {
                int id = Integer.parseInt(supplierId.getText().trim());
                loadTable(table, sql, id);

                Node chart = buildReportChart(table, labelColumn, valueColumn, chartKind);
                chartCard.getChildren().setAll(chartTitle, chart);
                VBox.setVgrow(chart, Priority.ALWAYS);
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Report Error", ex.getMessage());
            }
        });

        VBox.setVgrow(chartCard, Priority.ALWAYS);
        page.getChildren().addAll(back, title, sub, supplierTitle, suppliersTable, inputs, table, chartCard);
        setCenter(page);
    }

    private void openSupplierDateReportScreen(String reportTitle, String description, String sql,
                                              String labelColumn, String valueColumn, ChartKind chartKind) {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Button back = new Button("\u2190 Back to Reports");
        back.setStyle(GRAY);
        back.setOnAction(e -> showReports());

        Label title = new Label(reportTitle);
        title.setStyle(TITLE);

        Label sub = new Label(description);
        sub.setStyle(SUBTITLE);

        Label supplierTitle = new Label("Available Suppliers - choose one or type its ID");
        supplierTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        TableView<ObservableList<String>> suppliersTable = new TableView<>();
        suppliersTable.setPrefHeight(150);
        suppliersTable.setMinHeight(120);
        suppliersTable.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        loadTable(suppliersTable,
                "SELECT supplier_id AS Supplier_ID, supplier_name AS Supplier, company_name AS Company, phone_number AS Phone " +
                "FROM suppliers ORDER BY supplier_name");

        ComboBox<String> supplierChoice = new ComboBox<>();
        supplierChoice.setPromptText("Choose supplier");
        supplierChoice.setPrefWidth(300);
        loadSupplierChoices(supplierChoice);

        TextField supplierId = new TextField();
        supplierId.setPromptText("Supplier ID");
        supplierId.setPrefWidth(160);

        supplierChoice.setOnAction(e -> {
            String selected = supplierChoice.getValue();
            if (selected != null && selected.contains(" - ")) {
                supplierId.setText(selected.substring(0, selected.indexOf(" - ")).trim());
            }
        });

        suppliersTable.setOnMouseClicked(e -> {
            ObservableList<String> selected = suppliersTable.getSelectionModel().getSelectedItem();
            if (selected != null && !selected.isEmpty()) {
                supplierId.setText(selected.get(0));
            }
        });

        DatePicker start = new DatePicker();
        start.setPromptText("Start date");
        start.setPrefWidth(180);

        DatePicker end = new DatePicker();
        end.setPromptText("End date");
        end.setPrefWidth(180);

        Button run = new Button("Run Report");
        run.setStyle(BTN);

        HBox inputs = new HBox(10, supplierChoice, supplierId, start, end, run);
        inputs.setAlignment(Pos.CENTER_LEFT);

        TableView<ObservableList<String>> table = new TableView<>();
        table.setPrefHeight(280);
        table.setMinHeight(240);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        VBox chartCard = new VBox(10);
        chartCard.setStyle(CARD);
        chartCard.setPadding(new Insets(18));

        Label chartTitle = new Label(reportTitle + " Bar Chart");
        chartTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label placeholder = new Label("Choose supplier, select start/end date, then press Run Report.");
        placeholder.setStyle(SUBTITLE);
        chartCard.getChildren().addAll(chartTitle, placeholder);

        run.setOnAction(e -> {
            try {
                int id = Integer.parseInt(supplierId.getText().trim());
                Date s = requireDate(start, "Start date");
                Date en = requireDate(end, "End date");

                loadTable(table, sql,
                        id,
                        s.toString() + " 00:00:00",
                        en.toString() + " 23:59:59");

                Node chart = buildReportChart(table, labelColumn, valueColumn, chartKind);
                chartCard.getChildren().setAll(chartTitle, chart);
                VBox.setVgrow(chart, Priority.ALWAYS);
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Report Error", ex.getMessage());
            }
        });

        VBox.setVgrow(chartCard, Priority.ALWAYS);
        page.getChildren().addAll(back, title, sub, supplierTitle, suppliersTable, inputs, table, chartCard);
        setCenter(page);
    }

    /**
     * Same dedicated full-screen layout as openReportScreen, but first asks the user
     * for a start/end date (DatePicker) before running the query and drawing the chart.
     */
    private void openDateReportScreen(String reportTitle, String description,
                                       DateRangeSql sqlBuilder, String labelColumn,
                                       String valueColumn, ChartKind chartKind) {
        VBox page = new VBox(16);
        page.setPadding(new Insets(26));

        Button back = new Button("\u2190 Back to Reports");
        back.setStyle(GRAY);
        back.setOnAction(e -> showReports());

        Label title = new Label(reportTitle);
        title.setStyle(TITLE);

        Label sub = new Label(description);
        sub.setStyle(SUBTITLE);

        DatePicker start = new DatePicker();
        start.setPromptText("Start date");
        start.setPrefWidth(180);

        DatePicker end = new DatePicker();
        end.setPromptText("End date");
        end.setPrefWidth(180);

        Button run = new Button("Run Report");
        run.setStyle(BTN);

        HBox dates = new HBox(10, start, end, run);
        dates.setAlignment(Pos.CENTER_LEFT);

        TableView<ObservableList<String>> table = new TableView<>();
        table.setPrefHeight(280);
        table.setMinHeight(240);
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);

        VBox chartCard = new VBox(10);
        chartCard.setStyle(CARD);
        chartCard.setPadding(new Insets(18));

        Label chartTitle = new Label(reportTitle + " Bar Chart");
        chartTitle.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label placeholder = new Label("Pick a start and end date, then press Run Report.");
        placeholder.setStyle(SUBTITLE);
        chartCard.getChildren().addAll(chartTitle, placeholder);

        run.setOnAction(e -> {
            try {
                Date s = requireDate(start, "Start date");
                Date en = requireDate(end, "End date");
                String sql = sqlBuilder.build(s, en);
                loadTable(table, sql,
                        s.toString() + " 00:00:00",
                        en.toString() + " 23:59:59");

                Node chart = buildReportChart(table, labelColumn, valueColumn, chartKind);
                chartCard.getChildren().setAll(chartTitle, chart);
                VBox.setVgrow(chart, Priority.ALWAYS);
            } catch (Exception ex) {
                alert(Alert.AlertType.ERROR, "Report Error", ex.getMessage());
            }
        });

        VBox.setVgrow(chartCard, Priority.ALWAYS);

        page.getChildren().addAll(back, title, sub, dates, table, chartCard);
        setCenter(page);
    }

    /**
     * Builds a professional horizontal bar chart for every report screen.
     * Each bar shows what it represents, its exact value, rank, tooltip, and color.
     */
    private Node buildReportChart(TableView<ObservableList<String>> table,
                                   String labelColumn, String valueColumn, ChartKind chartKind) {
        java.util.List<String> columnNames = getLastLoadedColumnNames(table);
        int labelIdx = columnNames.indexOf(labelColumn);
        int valueIdx = columnNames.indexOf(valueColumn);

        if (labelIdx < 0 || valueIdx < 0 || table.getItems().isEmpty()) {
            return reportEmptyMessage("No data available for this report.");
        }

        java.util.List<ChartItem> items = new java.util.ArrayList<>();
        for (ObservableList<String> row : table.getItems()) {
            if (row.size() <= Math.max(labelIdx, valueIdx)) continue;

            String label = cleanChartLabel(row.get(labelIdx));
            double value = parseNumberSafe(row.get(valueIdx));
            if (value < 0) value = 0;
            items.add(new ChartItem(label, value));
        }

        if (items.isEmpty()) {
            return reportEmptyMessage("No numeric data available for this chart.");
        }

        items.sort((a, b) -> Double.compare(b.value, a.value));

        ChartItem highest = items.get(0);
        ChartItem lowest = items.get(items.size() - 1);

        java.util.List<ChartItem> visibleItems = items;
        if (visibleItems.size() > 12) {
            visibleItems = new java.util.ArrayList<>(visibleItems.subList(0, 12));
        }

        String valueSuffix = suffixForColumn(valueColumn);
        String cleanValueTitle = niceColumnName(valueColumn);
        String cleanLabelTitle = niceColumnName(labelColumn);

        VBox wrapper = new VBox(10);
        wrapper.setFillWidth(true);

        HBox summary = new HBox(12);
        summary.setAlignment(Pos.CENTER_LEFT);
        summary.getChildren().addAll(
                miniInfoCard("Highest", highest.label + " — " + formatChartValue(highest.value) + valueSuffix, "#059669"),
                miniInfoCard("Lowest", lowest.label + " — " + formatChartValue(lowest.value) + valueSuffix, "#dc2626"),
                miniInfoCard("Shown", visibleItems.size() + " of " + items.size() + " records", "#2563eb")
        );

        NumberAxis xAxis = new NumberAxis();
        CategoryAxis yAxis = new CategoryAxis();
        xAxis.setLabel(cleanValueTitle);
        yAxis.setLabel(cleanLabelTitle);
        xAxis.setForceZeroInRange(true);
        xAxis.setTickLabelFormatter(new javafx.util.StringConverter<Number>() {
            @Override
            public String toString(Number value) {
                if (value == null) return "0";
                return formatChartValue(value.doubleValue());
            }

            @Override
            public Number fromString(String text) {
                return 0;
            }
        });

        BarChart<Number, String> chart = new BarChart<>(xAxis, yAxis);
        chart.setLegendVisible(false);
        chart.setAnimated(false);
        chart.setCategoryGap(10);
        chart.setBarGap(4);
        chart.setMinHeight(390);
        chart.setPrefHeight(Math.max(420, 120 + visibleItems.size() * 46));
        chart.setStyle("-fx-font-size: 13px; -fx-background-color: white;");
        chart.setHorizontalGridLinesVisible(false);
        chart.setVerticalGridLinesVisible(true);
        chart.setAlternativeColumnFillVisible(false);
        chart.setAlternativeRowFillVisible(false);

        XYChart.Series<Number, String> series = new XYChart.Series<>();
        java.util.List<String> categories = new java.util.ArrayList<>();

        java.util.Map<String, Integer> categoryCounts = new java.util.HashMap<>();
        for (ChartItem item : visibleItems) {
            String baseLabel = shortenChartLabel(item.label, 38);
            int count = categoryCounts.getOrDefault(baseLabel, 0) + 1;
            categoryCounts.put(baseLabel, count);

            String displayLabel = count == 1 ? baseLabel : baseLabel + " (" + count + ")";
            categories.add(displayLabel);
            series.getData().add(new XYChart.Data<>(item.value, displayLabel));
        }

        yAxis.setCategories(FXCollections.observableArrayList(categories));
        chart.getData().add(series);

        applyProfessionalBarStyle(chart, series, visibleItems, cleanLabelTitle, cleanValueTitle, valueSuffix);

        Label note = new Label("Bars are sorted by value. The left list shows exactly who each bar belongs to and its value.");
        note.setStyle("-fx-font-size: 12px; -fx-text-fill: #6b7280;");

        VBox sideList = buildBarChartSideList(visibleItems, cleanLabelTitle, cleanValueTitle, valueSuffix);

        HBox chartLayout = new HBox(14);
        chartLayout.setAlignment(Pos.TOP_LEFT);
        chartLayout.setFillHeight(true);
        chartLayout.getChildren().addAll(sideList, chart);
        HBox.setHgrow(chart, Priority.ALWAYS);

        wrapper.getChildren().addAll(summary, note, chartLayout);
        return wrapper;
    }

    private static class ChartItem {
        String label;
        double value;

        ChartItem(String label, double value) {
            this.label = label;
            this.value = value;
        }
    }

    private void applyProfessionalBarStyle(BarChart<Number, String> chart,
                                           XYChart.Series<Number, String> series,
                                           java.util.List<ChartItem> items,
                                           String labelTitle,
                                           String valueTitle,
                                           String valueSuffix) {
        Platform.runLater(() -> {
            for (int i = 0; i < series.getData().size(); i++) {
                XYChart.Data<Number, String> data = series.getData().get(i);
                Node node = data.getNode();
                if (node == null) continue;

                ChartItem item = items.get(i);
                String color = CHART_COLORS[i % CHART_COLORS.length];
                node.setStyle("-fx-bar-fill: " + color + "; -fx-background-radius: 7;");

                String valueText = formatChartValue(item.value) + valueSuffix;
                Tooltip.install(node, new Tooltip(
                        labelTitle + ": " + item.label + "\n" +
                        valueTitle + ": " + valueText + "\n" +
                        "Rank: " + (i + 1)
                ));

                if (node instanceof StackPane) {
                    StackPane pane = (StackPane) node;
                    Label valueLabel = new Label(valueText);
                    valueLabel.setMouseTransparent(true);
                    valueLabel.setStyle(
                            "-fx-background-color: rgba(255,255,255,0.96);" +
                            "-fx-text-fill: #111827;" +
                            "-fx-font-size: 11px;" +
                            "-fx-font-weight: bold;" +
                            "-fx-padding: 2 7;" +
                            "-fx-background-radius: 8;" +
                            "-fx-border-color: #d1d5db;" +
                            "-fx-border-radius: 8;"
                    );
                    pane.getChildren().add(valueLabel);
                    StackPane.setAlignment(valueLabel, Pos.CENTER_RIGHT);
                    StackPane.setMargin(valueLabel, new Insets(0, 8, 0, 0));
                }
            }
        });
    }

    private VBox miniInfoCard(String title, String value, String accentColor) {
        VBox card = new VBox(4);
        card.setMinWidth(220);
        card.setPrefWidth(260);
        card.setPadding(new Insets(10, 12, 10, 12));
        card.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 12;"
        );

        Label t = new Label(title);
        t.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b; -fx-font-weight: bold;");

        Label v = new Label(value);
        v.setWrapText(true);
        v.setStyle("-fx-font-size: 13px; -fx-text-fill: " + accentColor + "; -fx-font-weight: bold;");

        card.getChildren().addAll(t, v);
        return card;
    }

    private VBox buildBarChartSideList(java.util.List<ChartItem> items,
                                      String labelTitle,
                                      String valueTitle,
                                      String valueSuffix) {
        VBox panel = new VBox(8);
        panel.setMinWidth(310);
        panel.setPrefWidth(360);
        panel.setMaxWidth(390);
        panel.setPadding(new Insets(12));
        panel.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 12;"
        );

        Label title = new Label(labelTitle + " Ranking");
        title.setStyle("-fx-font-size: 15px; -fx-font-weight: bold; -fx-text-fill: #111827;");

        Label sub = new Label(valueTitle + " by " + labelTitle);
        sub.setStyle("-fx-font-size: 12px; -fx-text-fill: #64748b;");

        HBox header = new HBox(8);
        header.setAlignment(Pos.CENTER_LEFT);
        header.setPadding(new Insets(6, 8, 6, 8));
        header.setStyle("-fx-background-color: #e5e7eb; -fx-background-radius: 8;");

        Label hName = new Label(labelTitle);
        hName.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #334155;");
        hName.setPrefWidth(210);

        Label hValue = new Label(valueTitle);
        hValue.setStyle("-fx-font-size: 12px; -fx-font-weight: bold; -fx-text-fill: #334155;");
        hValue.setPrefWidth(105);
        hValue.setAlignment(Pos.CENTER_RIGHT);

        header.getChildren().addAll(hName, hValue);

        panel.getChildren().addAll(title, sub, header);

        for (int i = 0; i < items.size(); i++) {
            ChartItem item = items.get(i);
            String color = CHART_COLORS[i % CHART_COLORS.length];

            HBox row = new HBox(8);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(8, 10, 8, 10));
            row.setStyle(
                    "-fx-background-color: white;" +
                    "-fx-background-radius: 9;" +
                    "-fx-border-color: #e2e8f0;" +
                    "-fx-border-radius: 9;"
            );

            Label rank = new Label(String.valueOf(i + 1));
            rank.setMinWidth(26);
            rank.setAlignment(Pos.CENTER);
            rank.setStyle(
                    "-fx-background-color: " + color + ";" +
                    "-fx-text-fill: white;" +
                    "-fx-font-weight: bold;" +
                    "-fx-font-size: 11px;" +
                    "-fx-background-radius: 30;"
            );

            Label name = new Label(shortenChartLabel(item.label, 28));
            name.setPrefWidth(185);
            name.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #111827;");

            Label value = new Label(formatChartValue(item.value) + valueSuffix);
            value.setPrefWidth(100);
            value.setAlignment(Pos.CENTER_RIGHT);
            value.setStyle("-fx-font-size: 13px; -fx-font-weight: bold; -fx-text-fill: #0f766e;");

            row.getChildren().addAll(rank, name, value);
            Tooltip.install(row, new Tooltip(
                    "Rank: " + (i + 1) + "\n" +
                    labelTitle + ": " + item.label + "\n" +
                    valueTitle + ": " + formatChartValue(item.value) + valueSuffix
            ));

            panel.getChildren().add(row);
        }

        return panel;
    }

    private Node reportEmptyMessage(String message) {
        VBox box = new VBox(8);
        box.setAlignment(Pos.CENTER);
        box.setMinHeight(270);
        box.setPrefHeight(320);
        box.setStyle(
                "-fx-background-color: #f8fafc;" +
                "-fx-background-radius: 12;" +
                "-fx-border-color: #e5e7eb;" +
                "-fx-border-radius: 12;" +
                "-fx-padding: 30;"
        );

        Label title = new Label("No Data");
        title.setStyle("-fx-font-size: 18px; -fx-font-weight: bold; -fx-text-fill: #64748b;");

        Label text = new Label(message);
        text.setWrapText(true);
        text.setAlignment(Pos.CENTER);
        text.setStyle("-fx-font-size: 14px; -fx-text-fill: #6b7280;");

        box.getChildren().addAll(title, text);
        return box;
    }

    private String cleanChartLabel(String value) {
        if (value == null || value.trim().isEmpty()) return "Unknown";
        return value.trim();
    }

    private String shortenChartLabel(String value, int max) {
        if (value == null) return "Unknown";
        String clean = value.trim();
        if (clean.length() <= max) return clean;
        return clean.substring(0, max - 3) + "...";
    }

    private String niceColumnName(String columnName) {
        if (columnName == null || columnName.trim().isEmpty()) return "Value";
        return columnName.replace('_', ' ');
    }

    private String suffixForColumn(String columnName) {
        String c = columnName == null ? "" : columnName.toLowerCase();

        if (c.contains("quantity") || c.contains("stock") || c.contains("units")) return " items";
        if (c.contains("number_of_sales")) return " sales";
        if (c.contains("number_of_purchases")) return " purchases";
        if (c.contains("number_of_medicines") || c.contains("expired_medicines")) return " medicines";
        if (c.contains("price") || c.contains("amount") || c.contains("revenue") ||
                c.contains("cost") || c.contains("spent") || c.contains("sales") ||
                c.contains("purchases") || c.contains("value") || c.equals("total")) return " NIS";

        return "";
    }

    private String formatChartValue(double value) {
        if (Double.isNaN(value) || Double.isInfinite(value)) return "0";
        if (value == Math.floor(value)) return String.format("%.0f", value);
        return String.format("%.2f", value);
    }

    private java.util.List<String> getLastLoadedColumnNames(TableView<ObservableList<String>> table) {
        java.util.List<String> names = new java.util.ArrayList<>();
        for (TableColumn<ObservableList<String>, ?> col : table.getColumns()) {
            names.add(col.getText());
        }
        return names;
    }

    private double parseNumberSafe(String s) {
        if (s == null || s.trim().isEmpty()) return 0;
        try {
            return Double.parseDouble(s.trim());
        } catch (NumberFormatException ex) {
            return 0;
        }
    }

    // =========================================================
    // SHARED UI AND DATABASE HELPERS
    // =========================================================
    private GridPane formGrid() {
        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.setStyle(CARD);
        return form;
    }

    private void loadTable(TableView<ObservableList<String>> table, String sql, Object... params) {
        table.getColumns().clear();
        table.getItems().clear();
        table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        table.setPlaceholder(new Label("No records found."));

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            setParams(ps, params);

            try (ResultSet rs = ps.executeQuery()) {
                ResultSetMetaData md = rs.getMetaData();
                int count = md.getColumnCount();

                for (int i = 1; i <= count; i++) {
                    final int index = i - 1;
                    String columnName = md.getColumnLabel(i);

                    TableColumn<ObservableList<String>, String> col = new TableColumn<>(columnName);
                    col.setCellValueFactory(data -> new ReadOnlyStringWrapper(data.getValue().get(index)));

                    double width = getColumnWidth(columnName);
                    col.setPrefWidth(width);
                    col.setMinWidth(width);
                    table.getColumns().add(col);
                }

                ObservableList<ObservableList<String>> rows = FXCollections.observableArrayList();
                while (rs.next()) {
                    ObservableList<String> row = FXCollections.observableArrayList();
                    for (int i = 1; i <= count; i++) {
                        Object value = rs.getObject(i);
                        row.add(value == null ? "" : value.toString());
                    }
                    rows.add(row);
                }
                table.setItems(rows);
            }
        } catch (Exception e) {
            alert(Alert.AlertType.ERROR, "Database Error", e.getMessage());
        }
    }

    private double getColumnWidth(String columnName) {
        String c = columnName.toLowerCase();
        if (c.equals("id") || c.endsWith("_id")) return 90;
        if (c.contains("date") || c.contains("expiry") || c.contains("updated")) return 170;
        if (c.contains("email")) return 260;
        if (c.contains("phone")) return 150;
        if (c.contains("customer") || c.contains("supplier") || c.contains("medicine") ||
            c.contains("warehouse") || c.contains("pharmacist") || c.contains("branch")) return 220;
        if (c.contains("description") || c.contains("address") || c.contains("location")) return 260;
        if (c.contains("status") || c.contains("gender") || c.contains("payment")) return 150;
        if (c.contains("total") || c.contains("price") || c.contains("quantity") ||
            c.contains("cost") || c.contains("amount")) return 160;
        return 160;
    }

    private String one(String sql) {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            if (rs.next()) return rs.getString(1);
        } catch (Exception e) {
            return "0";
        }
        return "0";
    }

    private void executeUpdate(String sql, Object... params) throws SQLException {
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            setParams(ps, params);
            ps.executeUpdate();
        }
    }

    private void setParams(PreparedStatement ps, Object... params) throws SQLException {
        for (int i = 0; i < params.length; i++) {
            ps.setObject(i + 1, params[i]);
        }
    }

    private boolean testConnection() {
        try (Connection con = DBConnection.getConnection()) {
            return con != null && !con.isClosed();
        } catch (Exception e) {
            return false;
        }
    }

    private Date requireDate(DatePicker picker, String fieldName) {
        if (picker.getValue() == null) {
            throw new IllegalArgumentException(fieldName + " is required. Please select a date.");
        }
        return Date.valueOf(picker.getValue());
    }

    private Date optionalDate(DatePicker picker) {
        if (picker.getValue() == null) return null;
        return Date.valueOf(picker.getValue());
    }

    private void setPickerValue(DatePicker picker, String value) {
        if (value == null || value.trim().isEmpty()) {
            picker.setValue(null);
            return;
        }
        picker.setValue(LocalDate.parse(value.substring(0, 10)));
    }

    private double getMedicinePrice(Connection con, int medicineId) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT selling_price FROM medicines WHERE medicine_id=? AND status='ACTIVE'")) {
            ps.setInt(1, medicineId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new SQLException("Medicine not found or inactive.");
                return rs.getDouble("selling_price");
            }
        }
    }

    private int[] getStockForUpdate(Connection con, int warehouseId, int medicineId) throws SQLException {
        try (PreparedStatement ps = con.prepareStatement(
                "SELECT quantity, reorder_level FROM inventory WHERE warehouse_id=? AND medicine_id=? FOR UPDATE")) {
            ps.setInt(1, warehouseId);
            ps.setInt(2, medicineId);
            try (ResultSet rs = ps.executeQuery()) {
                if (!rs.next()) throw new SQLException("Inventory record not found for this warehouse and medicine.");
                return new int[]{rs.getInt("quantity"), rs.getInt("reorder_level")};
            }
        }
    }

    private String inventoryStatus(int quantity, int reorderLevel) {
        if (quantity == 0) return "OUT_OF_STOCK";
        if (quantity <= reorderLevel) return "LOW_STOCK";
        return "AVAILABLE";
    }

    private String friendlyDbMessage(Exception ex) {
        String msg = ex.getMessage() == null ? "Unknown error." : ex.getMessage();

        if (msg.contains("fk_medicine_category") || msg.contains("category_id")) {
            return "The selected category does not exist. Please choose a category from the list.";
        }
        if (msg.contains("fk_medicine_supplier") || msg.contains("supplier_id")) {
            return "The selected supplier does not exist. Please choose a supplier from the list.";
        }
        if (msg.toLowerCase().contains("foreign key constraint fails")) {
            return "This record is linked to another table. Use an existing ID or deactivate/cancel instead of deleting.";
        }
        if (msg.toLowerCase().contains("duplicate")) {
            return "This value already exists. Please use a different value.";
        }
        if (msg.toLowerCase().contains("for input string")) {
            return "Please enter valid numbers in numeric fields.";
        }

        return msg;
    }

    private void alert(Alert.AlertType type, String title, String msg) {
        Alert a = new Alert(type);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
