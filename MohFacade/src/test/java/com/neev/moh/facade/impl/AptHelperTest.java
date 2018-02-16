package com.neev.moh.facade.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.neev.moh.facade.dto.appointment.SlotInfoDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:facade-context.xml"})
public class AptHelperTest extends TestCase {
	
	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

    private int hourSlotStart = 0;
    private int hourSlotSize = 1;
    private int hourSlotCount = 24;

    private int minuteSlotStart = 0;
    private int minuteSlotSize = 10;
    private int minuteSlotCount = 6;

    private List<String> statuses = Arrays.asList("status1", "status2", "status3", "status4", "status5");
    private List<Integer> lanes = Arrays.asList(1, 2, 3, 4, 5);

    @Autowired
    private AptHelper aptHelper;
    
    @Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

    @Test
    public void testGetSlotInfo() throws Exception {
        Set<SlotInfoDto> slotInfoDtos = new HashSet<>();
        for (Integer lane : lanes) {
            for (String status : statuses) {
                for (int hourIndex = 0; hourIndex < hourSlotCount; hourIndex++) {
                    int hourStart = hourSlotStart + hourIndex * hourSlotSize;
                    int hourEnd = hourSlotStart + (hourIndex + 1) * hourSlotSize;
                    for (int minuteIndex = 0; minuteIndex < minuteSlotCount; minuteIndex++) {
                        int minuteStart = minuteSlotStart + minuteIndex * minuteSlotSize;
                        int minuteEnd = minuteSlotStart + (minuteIndex + 1) * minuteSlotSize;
                        SlotInfoDto slotInfoDto = aptHelper.getSlotInfo(status, hourStart, minuteStart, hourEnd,
                                minuteEnd, lane);
                        assertFalse(slotInfoDtos.contains(slotInfoDto));
                        slotInfoDtos.add(slotInfoDto);
                        for (int i = 0; i < 3; i++) {
                            assertTrue(slotInfoDto == aptHelper.getSlotInfo(status, hourStart, minuteStart, hourEnd,
                                    minuteEnd, lane));
                        }
                    }
                }
            }
        }
    }
}