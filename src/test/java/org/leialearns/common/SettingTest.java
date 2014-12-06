package org.leialearns.common;

import org.junit.Test;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SettingTest {

    @Test(expected = IllegalStateException.class)
    public void testEmptySetting() {
        Setting<Boolean> setting = new Setting<>("test");
        setting.get();
    }

    @Test
    public void testDefault() {
        Setting<Boolean> setting;
        setting = new Setting<>("test", true);
        assertFalse(setting.isFixated());
        assertEquals(TRUE, setting.get());
        assertTrue(setting.isFixated());

        setting = new Setting<>("test", true);
        assertFalse(setting.isFixated());
        assertTrue(setting.offer(false));
        assertEquals(FALSE, setting.get());
        assertTrue(setting.isFixated());

        setting = new Setting<>("test", true);
        setting.set(false);
        assertEquals(FALSE, setting.get());
        assertFalse(setting.offer(true));
        assertEquals(FALSE, setting.get());
        assertTrue(setting.offer(false));
        assertEquals(FALSE, setting.get());
    }


}
