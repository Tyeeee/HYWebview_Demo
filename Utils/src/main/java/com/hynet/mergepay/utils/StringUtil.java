package com.hynet.mergepay.utils;

import com.hynet.mergepay.utils.constant.Regex;

import java.util.List;

public class StringUtil {

    private StringUtil() {
        // cannot be instantiated
    }

    public static String append(boolean isSeparate, String... content) {
        StringBuffer buffer = new StringBuffer();
        if (isSeparate) {
            for (int i = 0; i < content.length; i++) {
                if (i != content.length - 1) {
                    buffer.append(content[i]).append(Regex.COMMA.getRegext());
                } else {
                    buffer.append(content[i]);
                }
            }
        } else {
            for (String str : content) {
                buffer.append(str);
            }
        }
        LogUtil.getInstance().print(buffer.toString());
        return buffer.toString();
    }

    public static String append(boolean isSeparate, List<String> contents) {
        StringBuffer buffer = new StringBuffer();
        if (isSeparate) {
            for (int i = 0; i < contents.size(); i++) {
                if (i != contents.size() - 1) {
                    buffer.append(contents.get(i)).append(Regex.COMMA.getRegext());
                } else {
                    buffer.append(contents.get(i));
                }
            }
        } else {
            for (int i = 0; i < contents.size(); i++) {
                buffer.append(contents.get(i));
            }
        }
        LogUtil.getInstance().print(buffer.toString());
        return buffer.toString();
    }

    /**
     * 功能描述：去除字符串首部为"0"字符
     *
     * @param content 传入需要转换的字符串
     *
     * @return 转换后的字符串
     */
    public static String removeZero(String content) {
        char ch;
        String result = "";
        if (content != null && content.trim().length() > 0 && !content.trim().equalsIgnoreCase("null")) {
            try {
                for (int i = 0; i < content.length(); i++) {
                    ch = content.charAt(i);
                    if (ch != '0') {
                        result = content.substring(i);
                        break;
                    }
                }
            } catch (Exception e) {
                result = "";
            }
        } else {
            result = "";
        }
        return result;
    }

    /**
     * 功能描述：金额字符串转换：单位分转成单元
     *
     * @param content 传入需要转换的金额字符串
     *
     * @return 转换后的金额字符串
     */
    public static String fenToYuan(String content) {
        if (content == null)
            return "0.00";
        String s = content.toString();
        int len = -1;
        StringBuilder sb = new StringBuilder();
        if (s != null && s.trim().length() > 0 && !s.equalsIgnoreCase("null")) {
            s = removeZero(s);
            if (s != null && s.trim().length() > 0 && !s.equalsIgnoreCase("null")) {
                len = s.length();
                int tmp = s.indexOf("-");
                if (tmp >= 0) {
                    if (len == 2) {
                        sb.append("-0.0").append(s.substring(1));
                    } else if (len == 3) {
                        sb.append("-0.").append(s.substring(1));
                    } else {
                        sb.append(s.substring(0, len - 2)).append(".").append(s.substring(len - 2));
                    }
                } else {
                    if (len == 1) {
                        sb.append("0.0").append(s);
                    } else if (len == 2) {
                        sb.append("0.").append(s);
                    } else {
                        sb.append(s.substring(0, len - 2)).append(".").append(s.substring(len - 2));
                    }
                }
            } else {
                sb.append("0.00");
            }
        } else {
            sb.append("0.00");
        }
        return sb.toString();
    }

    /**
     * 功能描述：金额字符串转换：单位元转成单分
     *
     * @param content 传入需要转换的金额字符串
     *
     * @return 转换后的金额字符串
     */
    public static String yuanToFen(String content) {
        if (content == null)
            return "0";
        String s = content.toString();
        int posIndex = -1;
        String str = "";
        StringBuilder sb = new StringBuilder();
        if (s != null && s.trim().length() > 0 && !s.equalsIgnoreCase("null")) {
            posIndex = s.indexOf(".");
            if (posIndex > 0) {
                int len = s.length();
                if (len == posIndex + 1) {
                    str = s.substring(0, posIndex);
                    if (str == "0") {
                        str = "";
                    }
                    sb.append(str).append("00");
                } else if (len == posIndex + 2) {
                    str = s.substring(0, posIndex);
                    if (str == "0") {
                        str = "";
                    }
                    sb.append(str).append(s.substring(posIndex + 1, posIndex + 2)).append("0");
                } else if (len == posIndex + 3) {
                    str = s.substring(0, posIndex);
                    if (str == "0") {
                        str = "";
                    }
                    sb.append(str).append(s.substring(posIndex + 1, posIndex + 3));
                } else {
                    str = s.substring(0, posIndex);
                    if (str == "0") {
                        str = "";
                    }
                    sb.append(str).append(s.substring(posIndex + 1, posIndex + 3));
                }
            } else {
                sb.append(s).append("00");
            }
        } else {
            sb.append("0");
        }
        str = removeZero(sb.toString());
        if (str != null && str.trim().length() > 0 && !str.trim().equalsIgnoreCase("null")) {
            return str;
        } else {
            return "0";
        }
    }
}
