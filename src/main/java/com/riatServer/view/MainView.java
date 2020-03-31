package com.riatServer.view;

import com.riatServer.domain.Position;
import com.riatServer.domain.User;
import com.riatServer.repo.UsersRepo;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

@Route("main")
public class MainView extends VerticalLayout{
    private final UsersRepo usersRepo;
    private Grid<User> grid = new Grid<>(User.class);

    @Autowired
    public MainView(UsersRepo usersRepo) {
        this.usersRepo = usersRepo;
        addClassName("userListView");
        setSizeFull();
        configureGrid();

        add(grid);
        updateList();
    }

    private void configureGrid() {
        grid.addClassName("user-grid");
        grid.setSizeFull();
        grid.removeColumnByKey("position_id");
        grid.setColumns("name");
        grid.addColumn(user -> {
            Position position = user.getPosition_id();

            return position == null ? "-" : position.getName();
        }).setHeader("Position");
        grid.addColumns("telephone", "createDate", "updateDate");


        grid.getColumns().forEach(column -> column.setAutoWidth(true));

    }

    private void updateList() {
        grid.setItems(usersRepo.findAll());
    }
}