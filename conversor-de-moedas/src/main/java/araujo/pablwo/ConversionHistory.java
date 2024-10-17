package araujo.pablwo;

import java.time.LocalDateTime;

public record ConversionHistory(LocalDateTime date, String base, String target, double amount, double rate, double result) {
}
