package cn.hustxq.hust.bean;

/**
 * @Author hustxq.
 * @Date 2017/9/22 13:41
 */
public class EmployInfo {
    public int id;
    public String name;
    public String pos;
    public String note;
    public String time;

    @Override
    public String toString() {
        return "EmployInfo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pos='" + pos + '\'' +
                ", note='" + note + '\'' +
                ", time='" + time + '\'' +
                '}';
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
