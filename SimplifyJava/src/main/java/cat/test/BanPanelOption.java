package cat.test;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * TODO
 *
 * @author gaoguoxing
 * @version 1.0
 * @date 2023-01-06
 */
@Getter
@Setter
public class BanPanelOption implements Serializable {
    private Integer slienceTypeId;
    private String slienceTypeName;


}
