package model.OrderManagement;

public class ItemCartDTO {
    private int prodottoId;
    private int quantity;

    public ItemCartDTO(int prodottoId, int quantity) {
        this.prodottoId = prodottoId;
        this.quantity = quantity;
    }

    public int getProdottoId() {
        return prodottoId;
    }
    public void setProdottoId(int prodottoId) {
        this.prodottoId = prodottoId;
    }

    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}

