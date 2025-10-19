package com.transactions;

public final class TransMapper {

    private TransMapper() {}

    public static TransResponse toResponse(Trans t) {
        if (t == null) return null;
        TransResponse r = new TransResponse();
        r.setId(t.getId());
        r.setPayeeId(t.getPayeeId());
        r.setPayerId(t.getPayerId());
        r.setTimestamp(t.getTimestamp());
        r.setValue(t.getValue());
        return r;
    }
}

