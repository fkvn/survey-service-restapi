package survey.model.core;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "file")
public class File implements Serializable {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	@JsonIgnore
	private String name;

	@JsonIgnore
	private String type;

	@JsonValue
	private String url;

	@JsonIgnore
	private Long size;

	@Column(nullable = false)
	@JsonIgnore
	private Date date;

	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(nullable = false, name = "owner_id")
	private User owner;

	@Lob
	@JsonIgnore
	@Column(name = "file_data")
	private byte[] fileData;

	public Long getId() {

		return id;
	}

	public void setId(Long id) {

		this.id = id;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	public String getType() {

		return type;
	}

	public void setType(String type) {

		this.type = type;
	}

	public Long getSize() {

		return size;
	}

	public void setSize(Long size) {

		this.size = size;
	}

	public Date getDate() {

		return date;
	}

	public void setDate(Date date) {

		this.date = date;
	}

	public User getOwner() {

		return owner;
	}

	public void setOwner(User owner) {

		this.owner = owner;
	}

	public byte[] getFileData() {

		return fileData;
	}

	public void setFileData(byte[] fileData) {

		this.fileData = fileData;
	}

	public String getUrl() {

		return url;
	}

	public void setUrl(String url) {

		this.url = url;
	}

	public boolean equalTo(File file) {

		return Comparator.comparing(File::getName).thenComparing(File::getType)
				.thenComparing(File::getSize).compare(this, file) == 0
				&& Arrays.equals(this.getFileData(), file.getFileData());
	}

}
