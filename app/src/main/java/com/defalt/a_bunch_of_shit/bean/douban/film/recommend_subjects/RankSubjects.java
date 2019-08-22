package com.defalt.a_bunch_of_shit.bean.douban.film.recommend_subjects;
/*
 *  author: Derrick
 *  Time: 2019/8/21 10:54
 */

public class RankSubjects {

        private com.defalt.a_bunch_of_shit.bean.douban.film.Subjects subjects;
        private int rank;
        private int delta;
        public void setSubject(com.defalt.a_bunch_of_shit.bean.douban.film.Subjects subject) {
            this.subjects = subject;
        }
        public com.defalt.a_bunch_of_shit.bean.douban.film.Subjects getSubject() {
            return subjects;
        }

        public void setRank(int rank) {
            this.rank = rank;
        }
        public int getRank() {
            return rank;
        }

        public void setDelta(int delta) {
            this.delta = delta;
        }
        public int getDelta() {
            return delta;
        }

    }
