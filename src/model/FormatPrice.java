package model;

public class FormatPrice {
        public String execute(int price) {
                String formatPrice = (String.format("%,d", price));
                return formatPrice;
        }
}