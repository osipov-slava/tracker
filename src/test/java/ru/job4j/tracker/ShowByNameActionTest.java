package ru.job4j.tracker;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ShowByNameActionTest {

    private Output output;

    private Tracker tracker;

    private final String name = "Item";

    private final Item item = new Item(name);

    private ShowByNameAction showByNameAction;

    private Input input;

    private final String ln = System.lineSeparator();

    @Before
    public void init() {
        output = new StubOutput();
        tracker = new Tracker();

        tracker.add(item);
        showByNameAction = new ShowByNameAction(output);

        input = mock(Input.class);
    }

    @Test
    public void whenItemWasFoundByNameSuccessfully() {
        when(input.askStr(any(String.class))).thenReturn(name);
        showByNameAction.execute(input, tracker);
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + item + ln
        );
    }

    @Test
    public void whenItemWasntFoundByName() {
        String wrongName = "wrong name";
        when(input.askStr(any(String.class))).thenReturn(wrongName);
        showByNameAction.execute(input, tracker);
        assertThat(output.toString()).isEqualTo(
                "=== Find items by name ===" + ln
                        + "Заявки с именем: " + wrongName + " не найдены." + ln
        );
    }
}
