package com.khalti.checkout.helper;

import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;
import java.util.Map;

@Keep
public class Config {

    private String publicKey;
    private String productId;
    private String productName;
    private Long amount;
    private OnCheckOutListener onCheckOutListener;

    @Nullable
    private String mobile;
    @Nullable
    private String productUrl;
    @Nullable
    private Map<String, Object> additionalData;
    @Nullable
    private List<PaymentPreference> paymentPreferences;

    private Config() {

    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public Long getAmount() {
        return amount;
    }

    public OnCheckOutListener getOnCheckOutListener() {
        return onCheckOutListener;
    }

    @Nullable
    public String getMobile() {
        return mobile;
    }

    @Nullable
    public String getProductUrl() {
        return productUrl;
    }

    @Nullable
    public Map<String, Object> getAdditionalData() {
        return additionalData;
    }

    @Nullable
    public List<PaymentPreference> getPaymentPreferences() {
        return paymentPreferences;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public void setMobile(@Nullable String mobile) {
        this.mobile = mobile;
    }

    public void setProductUrl(@Nullable String productUrl) {
        this.productUrl = productUrl;
    }

    public void setAdditionalData(@Nullable Map<String, Object> additionalData) {
        this.additionalData = additionalData;
    }

    @Keep
    public static class Builder {

        private String publicKey;
        private String productId;
        private String productName;
        private Long amount;
        private OnCheckOutListener onCheckOutListener;

        @Nullable
        private String mobile;
        @Nullable
        private String productUrl;
        @Nullable
        private Map<String, Object> additionalData;
        @Nullable
        private List<PaymentPreference> paymentPreferences;

        public Builder(@NonNull String publicKey, @NonNull String productId, @NonNull String productName, @NonNull Long amount, @NonNull OnCheckOutListener onCheckOutListener) {
            this.publicKey = publicKey;
            this.productId = productId;
            this.productName = productName;
            this.amount = amount;
            this.onCheckOutListener = onCheckOutListener;
        }

        public Builder productUrl(@Nullable String productUrl) {
            this.productUrl = productUrl;
            return this;
        }

        public Builder mobile(@Nullable String mobile) {
            this.mobile = mobile;
            return this;
        }

        public Builder additionalData(@Nullable Map<String, Object> additionalData) {
            this.additionalData = additionalData;
            return this;
        }

        public Builder paymentPreferences(@Nullable List<PaymentPreference> paymentPreferences) {
            this.paymentPreferences = paymentPreferences;
            return this;
        }

        public Config build() {
            Config config = new Config();
            config.publicKey = publicKey;
            config.productId = productId;
            config.productName = productName;
            config.amount = amount;
            config.onCheckOutListener = onCheckOutListener;
            config.mobile = mobile;
            config.productUrl = productUrl;
            config.additionalData = additionalData;
            config.paymentPreferences = paymentPreferences;

            return config;
        }
    }
}