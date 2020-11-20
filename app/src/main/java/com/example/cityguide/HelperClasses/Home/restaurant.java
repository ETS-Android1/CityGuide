package com.example.cityguide.HelperClasses.Home;

public class restaurant {


        private String Name;
        private String Image;

        public restaurant()
        {

        }

        public restaurant(String name, String image) {
            Name = name;
            Image = image;
        }

        public String getName() {
            return Name;
        }

        public void setName(String name) {
            Name = name;
        }

        public String getImage() {
            return Image;
        }

        public void setImage(String image) {
            Image = image;
        }


}
