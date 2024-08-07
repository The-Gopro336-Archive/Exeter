package me.friendly.api.event;

import me.friendly.exeter.core.Exeter;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;

/**
 * @author Gopro336
 * This class is not part of Exeter, but was added as part of the
 * process in porting to forge.
 */
@Deprecated
public class EventProcessor {

    public static final EventProcessor INSTANCE = new EventProcessor();

    @SubscribeEvent
    public void onInput(InputEvent event) {
        me.friendly.exeter.events.InputEvent inputEvent
                = new me.friendly.exeter.events.InputEvent
                (me.friendly.exeter.events.InputEvent.Type.KEYBOARD_KEY_PRESS);

        Exeter.getInstance().getEventManager()
                .dispatch(inputEvent);
    }
}
