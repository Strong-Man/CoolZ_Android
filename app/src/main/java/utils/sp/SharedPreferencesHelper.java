package utils.sp;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by zpw on 2016/9/14.
 */

public class SharedPreferencesHelper {
    private static HashMap<String, SharedPreferencesHelper> sExecutorCache = new HashMap();
    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private Map<String, SoftReference<String>> mPrefCache = new HashMap();

    private SharedPreferencesHelper(Context context, String spName) {
        this.mSharedPreferences = context.getApplicationContext().getSharedPreferences(spName, 0);
        this.mEditor = this.mSharedPreferences.edit();
    }

    public static synchronized SharedPreferencesHelper getInstance(Context context, String spName) {

        SharedPreferencesHelper executor = (SharedPreferencesHelper) sExecutorCache.get(spName);
        if (executor == null) {
            executor = new SharedPreferencesHelper(context, spName);
            sExecutorCache.put(spName, executor);
        }

        return executor;
    }

    public boolean putBoolean(String name, boolean value) {
        if (this.mEditor != null && !TextUtils.isEmpty(name)) {
            this.mEditor.putBoolean(name, value);
            this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
            return true;
        } else {
            return false;
        }
    }

    public Boolean getBoolean(String name, boolean defaultValue) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            SoftReference softRef = (SoftReference) this.mPrefCache.get(name);
            if (softRef != null && !TextUtils.isEmpty((CharSequence) softRef.get())) {
                return Boolean.valueOf((String) softRef.get());
            } else {
                boolean value = defaultValue;
                if (this.mSharedPreferences.contains(name)) {
                    value = this.mSharedPreferences.getBoolean(name, defaultValue);
                }

                this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
                return Boolean.valueOf(value);
            }
        } else {
            return Boolean.valueOf(defaultValue);
        }
    }

    public boolean putString(String name, String value) {
        if (this.mEditor != null && !TextUtils.isEmpty(name)) {
            this.mEditor.putString(name, value);
            this.mPrefCache.put(name, new SoftReference(value));
            return true;
        } else {
            return false;
        }
    }

    public String getString(String name, String defaultValue) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            SoftReference softRef = (SoftReference) this.mPrefCache.get(name);
            if (softRef != null && !TextUtils.isEmpty((CharSequence) softRef.get())) {
                return (String) softRef.get();
            } else {
                String value = defaultValue;
                if (this.mSharedPreferences.contains(name)) {
                    value = this.mSharedPreferences.getString(name, defaultValue);
                }

                this.mPrefCache.put(name, new SoftReference(value));
                return value;
            }
        } else {
            return defaultValue;
        }
    }

    public boolean putLong(String name, long value) {
        if (this.mEditor != null && !TextUtils.isEmpty(name)) {
            this.mEditor.putLong(name, value);
            this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
            return true;
        } else {
            return false;
        }
    }

    public Long getLong(String name, long defaultValue) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            SoftReference softRef = (SoftReference) this.mPrefCache.get(name);
            if (softRef != null && !TextUtils.isEmpty((CharSequence) softRef.get())) {
                return Long.valueOf((String) softRef.get());
            } else {
                long value = defaultValue;
                if (this.mSharedPreferences.contains(name)) {
                    value = this.mSharedPreferences.getLong(name, defaultValue);
                }

                this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
                return Long.valueOf(value);
            }
        } else {
            return Long.valueOf(defaultValue);
        }
    }

    public boolean putInt(String name, int value) {
        if (this.mEditor != null && !TextUtils.isEmpty(name)) {
            this.mEditor.putInt(name, value);
            this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
            return true;
        } else {
            return false;
        }
    }

    public Integer getInt(String name, int defaultValue) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            SoftReference softRef = (SoftReference) this.mPrefCache.get(name);
            if (softRef != null && !TextUtils.isEmpty((CharSequence) softRef.get())) {
                return Integer.valueOf((String) softRef.get());
            } else {
                int value = defaultValue;
                if (this.mSharedPreferences.contains(name)) {
                    value = this.mSharedPreferences.getInt(name, defaultValue);
                }

                this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
                return Integer.valueOf(value);
            }
        } else {
            return Integer.valueOf(defaultValue);
        }
    }

    public boolean putFloat(String name, float value) {
        if (this.mEditor != null && !TextUtils.isEmpty(name)) {
            this.mEditor.putFloat(name, value);
            this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
            return true;
        } else {
            return false;
        }
    }

    public Float getFloat(String name, float defaultValue) {
        if (this.mSharedPreferences != null && !TextUtils.isEmpty(name)) {
            SoftReference softRef = (SoftReference) this.mPrefCache.get(name);
            if (softRef != null && !TextUtils.isEmpty((CharSequence) softRef.get())) {
                return Float.valueOf((String) softRef.get());
            } else {
                float value = defaultValue;
                if (this.mSharedPreferences.contains(name)) {
                    value = this.mSharedPreferences.getFloat(name, defaultValue);
                }

                this.mPrefCache.put(name, new SoftReference(String.valueOf(value)));
                return Float.valueOf(value);
            }
        } else {
            return Float.valueOf(defaultValue);
        }
    }

    public boolean putSet(String key, Set<String> value) {
        if (!TextUtils.isEmpty(key) && this.mSharedPreferences != null && this.mEditor != null) {
            this.mEditor.putStringSet(key, value);
            return true;
        } else {
            return false;
        }
    }

    public Set<String> getSet(String key, Set<String> defaultValue) {
        return !TextUtils.isEmpty(key) && this.mSharedPreferences != null ? this.mSharedPreferences.getStringSet(key, defaultValue) : defaultValue;
    }

    public boolean commit() {
        return this.mEditor == null ? false : this.mEditor.commit();
    }

    public void apply() {
        if (this.mEditor != null) {
            this.mEditor.apply();
        }
    }

    public boolean removeValue(String key) {
        if (!TextUtils.isEmpty(key) && this.mSharedPreferences != null && this.mEditor != null) {
            if (!this.mSharedPreferences.contains(key)) {
                return false;
            } else {
                this.mEditor.remove(key);
                this.mPrefCache.remove(key);
                return true;
            }
        } else {
            return false;
        }
    }

    public boolean putStringList(String name, List<String> value) {
        this.mEditor.remove(name).commit();
        return this.putListValue(name, value);
    }

    public List<String> getStringList(String name) {
        ArrayList linkedHashSet = new ArrayList();
        List set = this.getListValue(name, new ArrayList());
        if (set != null && set.size() > 0) {
            linkedHashSet.addAll(set);
        }

        return linkedHashSet;
    }

    public List<String> getListValue(String key, List<String> defValue) {
        try {
            if (this.mSharedPreferences == null) {
                return defValue;
            }

            String regularEx = "\\|";
            String listString = this.getString(key, (String) null);
            if (listString != null) {
                String[] values = listString.split("\\|");
                ArrayList list = new ArrayList(values.length);
                String[] var7 = values;
                int var8 = values.length;

                for (int var9 = 0; var9 < var8; ++var9) {
                    String value = var7[var9];
                    if (!TextUtils.isEmpty(value)) {
                        list.add(value);
                    }
                }

                return list;
            }
        } catch (Throwable var11) {
            ;
        }

        return defValue;
    }

    public boolean putListValue(String key, List<String> values) {
        try {
            if (this.mSharedPreferences == null || this.mEditor == null) {
                return false;
            }

            String regularEx = "|";
            String str = "";
            if (values != null && !values.isEmpty()) {
                Object[] objects = values.toArray();
                Object[] var6 = objects;
                int var7 = objects.length;

                for (int var8 = 0; var8 < var7; ++var8) {
                    Object obj = var6[var8];
                    str = str + obj.toString();
                    str = str + "|";
                }

                return this.putString(key, str);
            }
        } catch (Throwable var10) {

        }

        return false;
    }

    public boolean clearData() {
        if (this.mEditor == null) {
            return false;
        } else {
            this.mEditor.clear();
            return true;
        }
    }
}
