package com.example.ptbtb;

public class listArtikel {

        private String judul, desc, imageArtikel;

        public listArtikel(String judul, String desc, String imageArtikel) {
            this.judul = judul;
            this.desc = desc;
            this.imageArtikel = imageArtikel;
        }

        public  listArtikel(){
        }

        public String getJudul() {
            return judul;
        }

        public void setJudul(String judul) {
            this.judul = judul;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImageArtikel() {
            return imageArtikel;
        }

        public void setImageArtikel(String imageArtikel) {
            this.imageArtikel = imageArtikel;
        }
    }
