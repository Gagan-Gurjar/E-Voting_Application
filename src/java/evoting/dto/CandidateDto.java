/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

/**
 *
 * @author Win7
 */
public class CandidateDto {
    private String candidateId;
    private String CandidateName;
    private String party;
    private String symbol;

    public CandidateDto(String candidateId, String CandidateName, String party, String symbol) {
        this.candidateId = candidateId;
        this.CandidateName = CandidateName;
        this.party = party;
        this.symbol = symbol;
    }

    public String getCandidateId() {
        return candidateId;
    }

    public void setCandidateId(String candidateId) {
        this.candidateId = candidateId;
    }

    public String getCandidateName() {
        return CandidateName;
    }

    public void setCandidateName(String CandidateName) {
        this.CandidateName = CandidateName;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
    
}
