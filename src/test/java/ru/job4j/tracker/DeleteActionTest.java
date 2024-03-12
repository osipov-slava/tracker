package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class DeleteActionTest {

    private Output output;

    private Tracker tracker;

    private DeleteAction deleteAction;

    private Input input;

    private final String ln = System.lineSeparator();

    @Before
    public void init() {
        output = new StubOutput();
        tracker = new Tracker();
        tracker.add(new Item("Item"));
        deleteAction = new DeleteAction(output);

        input = mock(Input.class);
    }

    @Test
    public void whenItemWasDeletedSuccessfully() {
        when(input.askInt(any(String.class))).thenReturn(1);
        deleteAction.execute(input, tracker);
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Заявка удалена успешно." + ln
        );
    }

    @Test
    public void whenItemWasDeletedUnsuccessfully() {
        when(input.askInt(any(String.class))).thenReturn(2);
        deleteAction.execute(input, tracker);
        assertThat(output.toString()).isEqualTo(
                "=== Delete item ===" + ln
                        + "Ошибка удаления заявки." + ln
        );
    }
}
