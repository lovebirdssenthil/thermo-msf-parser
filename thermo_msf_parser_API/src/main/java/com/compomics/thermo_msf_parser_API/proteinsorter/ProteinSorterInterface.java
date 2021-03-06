package com.compomics.thermo_msf_parser_API.proteinsorter;


import com.compomics.thermo_msf_parser_API.lowmeminstance.model.ProteinLowMem;
import java.util.Comparator;

/**
 * Created by IntelliJ IDEA.
 * User: Davy
 * Date: 4/24/12
 * Time: 4:27 PM
 * To change this template use File | Settings | File Templates.
 *
 * @author Davy Maddelein
 * @version $Id: $Id
 */
public interface ProteinSorterInterface extends Comparator<ProteinLowMem> {

    /*
    a sorting method to sort proteins by their accessions in alphabetical order

    @param boolean lAtoZ true: from A to Z false: from Z to A
    */

    /**
     * <p>compareProteinByAccession.</p>
     *
     * @param lAtoZ a {@link java.lang.Boolean} object.
     */
    public void compareProteinByAccession(Boolean lAtoZ);

    /*
    a sorting method to sort proteins by the number of peptides they have

    @param boolean l1to20 true: from lowest to highest false: from highest to lowest
    */

    /**
     * <p>compareProteinByNumberOfPeptides.</p>
     *
     * @param l1to20 a {@link java.lang.Boolean} object.
     */
    public void compareProteinByNumberOfPeptides(Boolean l1to20);

}
