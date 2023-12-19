package com.example.ptbtb;

public class TawarData {
        private String dataTitlePenerima;
        private String dataDetailPenerima;
        private String dataBarterPenerima;
        private String dataImagePenerima;

        private String usernamePenerima;
        private String user_idPenerima,status;
        private String user_idTukar, usernameTukar, dataTitleTukar, dataDetailTukar, dataBarterTukar, dataImageTukar;

        String key;

        public TawarData() {
            // Konstruktor kosong diperlukan untuk Firebase
        }

    public TawarData(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public TawarData(String user_idPenerima, String usernamePenerima, String dataTitlePenerima,
                     String dataDetailPenerima, String dataBarterPenerima, String dataImagePenerima,
                     String user_idTukar, String usernameTukar, String dataTitleTukar,
                     String dataDetailTukar, String dataBarterTukar, String dataImageTukar,String status) {


        this.user_idPenerima = user_idPenerima;
        this.usernamePenerima = usernamePenerima;
        this.dataTitlePenerima = dataTitlePenerima;
        this.dataDetailPenerima = dataDetailPenerima;
        this.dataBarterPenerima = dataBarterPenerima;
        this.dataImagePenerima = dataImagePenerima;


        // Inisialisasi data tukardengan
        this.user_idTukar = user_idTukar;
        this.usernameTukar = usernameTukar;
        this.dataTitleTukar = dataTitleTukar;
        this.dataDetailTukar = dataDetailTukar;
        this.dataBarterTukar = dataBarterTukar;
        this.dataImageTukar = dataImageTukar;

        this.status = status;
    }


    public String getDataImagePenerima() {
        return dataImagePenerima;
    }

    public void setDataImagePenerima(String dataImagePenerima) {
        this.dataImagePenerima = dataImagePenerima;
    }

    public String getDataTitlePenerima() {
        return dataTitlePenerima;
    }

    public void setDataTitlePenerima(String dataTitlePenerima) {
        this.dataTitlePenerima = dataTitlePenerima;
    }

    public String getDataDetailPenerima() {
        return dataDetailPenerima;
    }

    public void setDataDetailPenerima(String dataDetailPenerima) {
        this.dataDetailPenerima = dataDetailPenerima;
    }

    public String getDataBarterPenerima() {
        return dataBarterPenerima;
    }

    public void setDataBarterPenerima(String dataBarterPenerima) {
        this.dataBarterPenerima = dataBarterPenerima;
    }

    public String getUsernamePenerima() {
        return usernamePenerima;
    }

    public void setUsernamePenerima(String usernamePenerima) {
        this.usernamePenerima = usernamePenerima;
    }

    public String getUser_idPenerima() {
        return user_idPenerima;
    }

    public void setUser_idPenerima(String user_idPenerima) {
        this.user_idPenerima = user_idPenerima;
    }

    public String getUser_idTukar() {
        return user_idTukar;
    }

    public void setUser_idTukar(String user_idTukar) {
        this.user_idTukar = user_idTukar;
    }

    public String getUsernameTukar() {
        return usernameTukar;
    }

    public void setUsernameTukar(String usernameTukar) {
        this.usernameTukar = usernameTukar;
    }

    public String getDataTitleTukar() {
        return dataTitleTukar;
    }

    public void setDataTitleTukar(String dataTitleTukar) {
        this.dataTitleTukar = dataTitleTukar;
    }

    public String getDataDetailTukar() {
        return dataDetailTukar;
    }

    public void setDataDetailTukar(String dataDetailTukar) {
        this.dataDetailTukar = dataDetailTukar;
    }

    public String getDataBarterTukar() {
        return dataBarterTukar;
    }

    public void setDataBarterTukar(String dataBarterTukar) {
        this.dataBarterTukar = dataBarterTukar;
    }

    public String getDataImageTukar() {
        return dataImageTukar;
    }

    public void setDataImageTukar(String dataImageTukar) {
        this.dataImageTukar = dataImageTukar;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
