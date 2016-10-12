package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import seedu.address.model.person.ReadOnlyTask;

public class PersonCard extends UiPart{

    private static final String FXML = "PersonListCard.fxml";

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;
    @FXML
    private Label id;
    @FXML
    private Label dateTimeInfo;
    @FXML
    private Label tags;

    private ReadOnlyTask task;
    private int displayedIndex;

    public PersonCard(){

    }

    public static PersonCard load(ReadOnlyTask person, int displayedIndex){
        PersonCard card = new PersonCard();
        card.task = person;
        card.displayedIndex = displayedIndex;
        return UiPartLoader.loadUiPart(card);
    }

    @FXML
    public void initialize() {
        String dateInfo = (task.getIsTask()||task.getIsEvent())?(task.getIsTask()?" by " + task.getDueDate():
            " from " + task.getStartTime() +" to "+ task.getEndTime()):"";
        title.setText(task.getName().fullName);
        id.setText(displayedIndex + ". ");
        dateTimeInfo.setText(dateInfo);
        tags.setText(task.tagsString());
    }

    public HBox getLayout() {
        return cardPane;
    }

    @Override
    public void setNode(Node node) {
        cardPane = (HBox)node;
    }

    @Override
    public String getFxmlPath() {
        return FXML;
    }
}
