package com.cmfirsttech.y2.api.entity.prototype;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ActionDiagramPrototypeId implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="@@MSG_P")
	public Integer prototypeSurrogate;

	@Column(name="@@ELM")
	public Integer elementNo;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((elementNo == null) ? 0 : elementNo.hashCode());
		result = prime * result + ((prototypeSurrogate == null) ? 0 : prototypeSurrogate.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ActionDiagramPrototypeId other = (ActionDiagramPrototypeId) obj;
		if (elementNo == null) {
			if (other.elementNo != null)
				return false;
		} else if (!elementNo.equals(other.elementNo))
			return false;
		if (prototypeSurrogate == null) {
			if (other.prototypeSurrogate != null)
				return false;
		} else if (!prototypeSurrogate.equals(other.prototypeSurrogate))
			return false;
		return true;
	}


}
