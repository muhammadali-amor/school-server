package ali.school_server.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class ResourceNotFoundException extends RuntimeException {
    private final String resourceName;
    private final String fieldName;
    private final Object fieldValue;
    private final Integer status;

    public ResourceNotFoundException(Integer status, String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s - %s not found with %s : '%s'", status, resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
        this.status = status;
    }

    public String getResourceName() {
        return resourceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Object getFieldValue() {
        return fieldValue;
    }

    public Integer getStatus() {
        return status;
    }
}
