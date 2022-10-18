package seedu.rc4hdb.ui;

import static seedu.rc4hdb.commons.util.CollectionUtil.requireAllNonNull;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Region;
import seedu.rc4hdb.model.resident.Resident;
import seedu.rc4hdb.model.resident.fields.Email;
import seedu.rc4hdb.model.resident.fields.Field;
import seedu.rc4hdb.model.resident.fields.Gender;
import seedu.rc4hdb.model.resident.fields.House;
import seedu.rc4hdb.model.resident.fields.MatricNumber;
import seedu.rc4hdb.model.resident.fields.Name;
import seedu.rc4hdb.model.resident.fields.Phone;
import seedu.rc4hdb.model.resident.fields.Room;
import seedu.rc4hdb.model.resident.fields.Tag;

/**
 * Panel containing the list of persons.
 */
public class ResidentTableView extends UiPart<Region> {

    private static final String FXML = "ResidentTableView.fxml";

    private final TableColumn<Resident, Field> emailColumn = new TableColumn<>(Email.IDENTIFIER);
    private final TableColumn<Resident, Field> genderColumn = new TableColumn<>(Gender.IDENTIFIER);
    private final TableColumn<Resident, Field> houseColumn = new TableColumn<>(House.IDENTIFIER);
    private final TableColumn<Resident, Field> indexColumn = new TableColumn<>(Field.INDEX_IDENTIFIER);
    private final TableColumn<Resident, Field> matricColumn = new TableColumn<>(MatricNumber.IDENTIFIER);
    private final TableColumn<Resident, Field> nameColumn = new TableColumn<>(Name.IDENTIFIER);
    private final TableColumn<Resident, Field> phoneColumn = new TableColumn<>(Phone.IDENTIFIER);
    private final TableColumn<Resident, Field> roomColumn = new TableColumn<>(Room.IDENTIFIER);
    private final TableColumn<Resident, Field> tagColumn = new TableColumn<>(Tag.IDENTIFIER);

    @FXML
    private TableView<Resident> residentTableView;
    private ObservableList<String> observableFields = FXCollections.observableArrayList();

    /**
     * Creates a {@code ResidentTableView} with the given {@code ObservableList}.
     */
    public ResidentTableView(ObservableList<Resident> residentList, ObservableList<String> observableFields) {
        super(FXML);
        requireAllNonNull(residentList, observableFields);

        this.residentTableView.setItems(residentList);
        addColumns();
        populateRows();
        configureTableProperties();

        this.observableFields.setAll(observableFields);
        this.observableFields.addListener(getListChangeListener());
    }

    /**
     * Sets the columns of the table with the formatters.
     */
    private void addColumns() {
        residentTableView.getColumns().add(indexColumn);
        residentTableView.getColumns().add(nameColumn);
        residentTableView.getColumns().add(phoneColumn);
        residentTableView.getColumns().add(emailColumn);
        residentTableView.getColumns().add(roomColumn);
        residentTableView.getColumns().add(genderColumn);
        residentTableView.getColumns().add(houseColumn);
        residentTableView.getColumns().add(matricColumn);
        residentTableView.getColumns().add(tagColumn);
    }

    /**
     * Populates the columns with data from the given {@code ObservableList}.
     */
    private void populateRows() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        roomColumn.setCellValueFactory(new PropertyValueFactory<>("room"));
        indexColumn.setCellFactory(this::populateIndexColumn);
        genderColumn.setCellValueFactory(new PropertyValueFactory<>("gender"));
        houseColumn.setCellValueFactory(new PropertyValueFactory<>("house"));
        matricColumn.setCellValueFactory(new PropertyValueFactory<>("matricNumber"));
        tagColumn.setCellValueFactory(new PropertyValueFactory<>("tags"));
    }

    /**
     * Code referenced from:
     * https://stackoverflow.com/questions/33353014/creating-a-row-index-column-in-javafx
     */
    private TableCell<Resident, Field> populateIndexColumn(TableColumn<Resident, Field> column) {
        return new TableCell<>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(index + 1));
                }
            }
        };
    }

    /**
     * Stylizes the {@code ResidentTableView} to maximise column width.
     */
    private void configureTableProperties() {
        residentTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        indexColumn.setResizable(false);
        indexColumn.setPrefWidth(70);

        nameColumn.setMinWidth(300);

        phoneColumn.setResizable(false);
        phoneColumn.setPrefWidth(120);

        emailColumn.setMinWidth(300);

        roomColumn.setResizable(false);
        roomColumn.setPrefWidth(140);

        genderColumn.setResizable(false);
        genderColumn.setPrefWidth(90);

        houseColumn.setResizable(false);
        houseColumn.setPrefWidth(90);

        matricColumn.setResizable(false);
        matricColumn.setPrefWidth(140);
    }



    private ListChangeListener<String> getListChangeListener() {
        return c -> {
            // Reset column visibilities
            residentTableView.getColumns().forEach(column -> column.setVisible(true));

            // Filter all columns (including index column) to obtain required columns
            // Recall that column headers is in title-case, i.e. first letter is capitalised
            residentTableView.getColumns().stream()
                    .filter(column -> this.observableFields.contains(column.getText().toLowerCase()))
                    .forEach(column -> column.setVisible(false));
        };
    }

    public void setObservableFields(ObservableList<String> list) {
        this.observableFields.setAll(list);
    }
}

