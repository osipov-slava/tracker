package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowByIdActionTest {
    private Output output;

    private Tracker tracker;

    private final Item item = new Item("Item");

    private ShowByIdAction showByIdAction;

    private Input input;

    private final String ln = System.lineSeparator();

    @Before
    public void init() {
        output = new StubOutput();
        tracker = new Tracker();

        tracker.add(item);
        showByIdAction = new ShowByIdAction(output);

        input = mock(Input.class);

        when(input.askInt(any(String.class))).thenReturn(1);
    }

    @Test
    public void whenItemWasFoundByIdSuccessfully() {
        when(input.askInt(any(String.class))).thenReturn(1);
        showByIdAction.execute(input, tracker);
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasntFoundById() {
        int id = 2;
        when(input.askInt(any(String.class))).thenReturn(id);
        showByIdAction.execute(input, tracker);
        assertThat(output.toString()).isEqualTo(
                "=== Find item by id ===" + ln
                        + "Заявка с введенным id: " + id + " не найдена." + ln
        );
    }
}
