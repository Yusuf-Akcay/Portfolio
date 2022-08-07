package de.yusuf.controller.employeeManager;

import java.time.LocalDate;

public record Project( String name, LocalDate startDate, LocalDate endDate ) {
}
