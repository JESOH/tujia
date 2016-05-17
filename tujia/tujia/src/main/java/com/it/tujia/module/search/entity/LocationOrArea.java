package com.it.tujia.module.search.entity;

import java.util.List;

/**
 * Created by Administrator on 2015/11/23.
 */
public class LocationOrArea {


    private int errorCode;
    private Object errorMessage;

    private Content content;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMessage(Object errorMessage) {
        this.errorMessage = errorMessage;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public Content getContent() {
        return content;
    }

    public static class Content {
        private int gType;
        private boolean isNew;
        private String label;
        private int sType;
        private List<?> items;


        private List<SubGroups> subGroups;

        public void setGType(int gType) {
            this.gType = gType;
        }

        public void setIsNew(boolean isNew) {
            this.isNew = isNew;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        public void setSType(int sType) {
            this.sType = sType;
        }

        public void setItems(List<?> items) {
            this.items = items;
        }

        public void setSubGroups(List<SubGroups> subGroups) {
            this.subGroups = subGroups;
        }

        public int getGType() {
            return gType;
        }

        public boolean isIsNew() {
            return isNew;
        }

        public String getLabel() {
            return label;
        }

        public int getSType() {
            return sType;
        }

        public List<?> getItems() {
            return items;
        }

        public List<SubGroups> getSubGroups() {
            return subGroups;
        }

        public static class SubGroups {
            private int gType;
            private boolean isNew;
            private String label;
            private int sType;
            /**
             * gType : 2
             * isNew : false
             * isSelected : false
             * label : 798艺术区
             * type : 19
             * value : 5571
             */

            private List<Items> items;
            private List<?> subGroups;

            public void setGType(int gType) {
                this.gType = gType;
            }

            public void setIsNew(boolean isNew) {
                this.isNew = isNew;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public void setSType(int sType) {
                this.sType = sType;
            }

            public void setItems(List<Items> items) {
                this.items = items;
            }

            public void setSubGroups(List<?> subGroups) {
                this.subGroups = subGroups;
            }

            public int getGType() {
                return gType;
            }

            public boolean isIsNew() {
                return isNew;
            }

            public String getLabel() {
                return label;
            }

            public int getSType() {
                return sType;
            }

            public List<Items> getItems() {
                return items;
            }

            public List<?> getSubGroups() {
                return subGroups;
            }

            public static class Items {
                private int gType;
                private boolean isNew;
                private boolean isSelected;
                private String label;
                private int type;
                private String value;

                public void setGType(int gType) {
                    this.gType = gType;
                }

                public void setIsNew(boolean isNew) {
                    this.isNew = isNew;
                }

                public void setIsSelected(boolean isSelected) {
                    this.isSelected = isSelected;
                }

                public void setLabel(String label) {
                    this.label = label;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public void setValue(String value) {
                    this.value = value;
                }

                public int getGType() {
                    return gType;
                }

                public boolean isIsNew() {
                    return isNew;
                }

                public boolean isIsSelected() {
                    return isSelected;
                }

                public String getLabel() {
                    return label;
                }

                public int getType() {
                    return type;
                }

                public String getValue() {
                    return value;
                }
            }
        }
    }
}
