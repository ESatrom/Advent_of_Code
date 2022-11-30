package main2021;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Day8 {
	public static void main(String[] args) {
		Scanner in = new Scanner(input);
		int res = 0;
		while(in.hasNextLine()) {
			String line = in.nextLine();
			String[] inNums = line.substring(0, 58).split(" ");
			String[] outNums = line.substring(61).split(" ");
			String chars = "abcdefg";
			char[] segs = new char[7];
			String one = "";
			for(String n : inNums) { if(n.length()==2) { one=n; break; } }
			for(String n : inNums) { if(n.length()==3) { for(String s : n.split("|")) { if(!one.contains(s)) { segs[0]=s.charAt(0); break; } } } }
			//a
			String four = "";
			for(String n : inNums) { if(n.length()==4) { four=n; break; } }
			four = four.substring(0,four.indexOf(one.charAt(0)))+four.substring(four.indexOf(one.charAt(0))+1);
			four = four.substring(0,four.indexOf(one.charAt(1)))+four.substring(four.indexOf(one.charAt(1))+1);
			for(String n : inNums) {
				if(n.length()==6) {
					String miss = "";
					for(String s : chars.split("|")) { if(!n.contains(s)) { miss = s; break; } }
					if(one.contains(miss)) { segs[2]=miss.charAt(0); }
					else if(four.contains(miss)) { segs[3]=miss.charAt(0); }
					else { segs[4]=miss.charAt(0); }
				}
			}
			//acde
			one = one.substring(0,one.indexOf(segs[2]))+one.substring(one.indexOf(segs[2])+1);
			segs[5] = one.charAt(0);
			//acdef
			four = four.substring(0,four.indexOf(segs[3]))+four.substring(four.indexOf(segs[3])+1);
			segs[1] = four.charAt(0);
			//abcdef
			String g = "";
			for(char c : segs) { g+=c; }
			for(String s : chars.split("|")) { if(!g.contains(s)) { segs[6]=s.charAt(0); } }
			//abcdefg
			Map<Set<Character>, Integer> segMap = new HashMap<>();
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[1],segs[2],segs[4],segs[5],segs[6])), 0);
			segMap.put(new HashSet<>(Arrays.asList(segs[2],segs[5])), 1);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[2],segs[3],segs[4],segs[6])), 2);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[2],segs[3],segs[5],segs[6])), 3);
			segMap.put(new HashSet<>(Arrays.asList(segs[1],segs[2],segs[3],segs[5])), 4);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[1],segs[3],segs[5],segs[6])), 5);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[1],segs[3],segs[4],segs[5],segs[6])), 6);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[2],segs[5])), 7);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[1],segs[2],segs[3],segs[4],segs[5],segs[6])), 8);
			segMap.put(new HashSet<>(Arrays.asList(segs[0],segs[1],segs[2],segs[3],segs[5],segs[6])), 9);
			
			String num = "";
			for(String s : outNums) {
				Set<Character> xx = new HashSet<>();
				for(String o : s.split("|")) { xx.add(o.charAt(0)); }
				num+=segMap.get(xx);
			}
			res += Integer.parseInt(num);
		}
		in.close();
		System.out.println(res);
	}
	static final String input = "caebgd dagc eabgd aebfgc fbdacge edg dg dbgcef eabfd cgeba | gbcdae dagc acgd gd\r\n" + 
			"fgcbed bcedga cafb acgfd cgabd cgf dfaeg dfbcga bceagdf fc | cf gdfea fdagbc dfacg\r\n" + 
			"gaed egcbdf edagcf fcaegdb facge afg ga ecbaf fgced fgdabc | cgeaf gfa ga efbac\r\n" + 
			"cbdeg cbgeadf cgdfa cgdbae decgf efg cebf egbfdc fgbead ef | fge gfe dcegabf gdcfe\r\n" + 
			"bacd cgdbe ecgbf cd cde degba cfaegd gfadbe gbdace baegcdf | cfgdea dce bacd cabd\r\n" + 
			"gdbafc cedagb cebga bc edcb gbdefa cebgdaf agfce acb bgdae | afgedb bdce dabeg dbec\r\n" + 
			"fadce bad edafbc cgbfa edcagf dacbge dfabc bgecadf edbf bd | efbd baegdc dab db\r\n" + 
			"fcaed eb afcdge cfbae gacbf edab becgdaf gcedbf efb bdecaf | be gadbfec eb cedfa\r\n" + 
			"cdgbea ebcga feacb age ge afcdgb faegdc acbgd agcfdbe bdeg | gedcab dcagb gea edbg\r\n" + 
			"eda bgeaf eabdcg ad ebgdacf egdfac ecdbfg dcfge acdf dagfe | ad ad da beagf\r\n" + 
			"cegfa bf ecfabd fgcb egcafd ebfga bgade afb eagbfc dgfbeca | bcegaf fcgb adgbe bcgf\r\n" + 
			"fegacb dbagc gdcae fbcd bc dfbgca gdbfa cgb adefbg ecdabgf | cgb fbgdac cabfdg acdeg\r\n" + 
			"gfbead cfbgea cfeb bf agcfb baf cdgaf begacd aebcg acgdfbe | fab gdcbeaf afcbeg bfa\r\n" + 
			"df acgbf bedag gecbdf dgefacb fdagb gfadeb fbd abgecd efad | bcafg cafdgeb bdf fd\r\n" + 
			"defbc dg gadc caebdfg bceagf gecba adegbf ebagdc gecdb dbg | bfgdea aefbdcg dgb dbceg\r\n" + 
			"cegbd cafegb gedcab gcebdf ecdf dfbag fbgde ef efg fbaecgd | fedc ef fdec ebagcf\r\n" + 
			"dbfag gabdcf dfgbeac efdcag gbcfe ed efbadg efd bade efbgd | bagfd def ed efgbda\r\n" + 
			"gafbcde gfbdc fc geafbc cgf ecdfgb cfde dbgac gdfeb gedabf | fced gfc cdbag gfc\r\n" + 
			"ef adbefg efdbc cfabd fcea fbe cebdg cbfgda abecfd dagbefc | degcbaf ef fecdagb dafebc\r\n" + 
			"acdbf abefgd ea dgcfe acedgf agec faedcgb afe gcbdfe fecad | geca gebadfc fgeabcd efdca\r\n" + 
			"agfecd acbg aefdb bg gdcfa gdafb cebdagf gfb dgcafb cgbefd | fbg dgafc fbg bafdg\r\n" + 
			"db ebafd dcafeg bcedag eafgb bcdf ecafd adb eabdfc cdbgaef | bgafe dfbea bd daefc\r\n" + 
			"egfacd cegfa afbdg facgbe de dfegbc cdae edafg egd ecdfbga | edg defcga aegfc de\r\n" + 
			"abdgfce begca egcbf ag gdafbc afge fbdcge daecb cefabg cag | gbfced ga cgedbfa ag\r\n" + 
			"faed abcedf eafdbcg aebcf cbagf cgebda ef fbe cbgfde bdcae | afbcg dafe fbe fbe\r\n" + 
			"bcea cfdae dcafeb ab cfbdg dgeabf afbcd baf fdacge gacfdbe | baf decgfab fab abce\r\n" + 
			"dbeacg agefcd agbefdc cagfe ae aeg aedf gdafc bfceg bacdfg | gea begcf fdcage ea\r\n" + 
			"cagdeb ca bcfgad badgef cbaf cgbefad adc fcdga gdfec bfagd | aecbdfg afcb ac bcfa\r\n" + 
			"bc begda gebfcad bfcd dbeac abdfce dcafe cbe cafdge bgcfae | bcdf ebdga gbfecda bce\r\n" + 
			"dfacgb edbcg gfedab egfab bac begac fcea ca dgfcabe ebacfg | bac cba bgcde ca\r\n" + 
			"fgacbe dgeba agebf afb fbcaed eafcgd fb dfegacb bgfc cfgea | cfegabd ceafg bf cbadef\r\n" + 
			"fgcab ceabdgf fdabc da gdabcf gdcaeb ecfbag fadg cdbef acd | fagd gfad fcbgae adgf\r\n" + 
			"afbgecd cedga cdfb ebgdcf gbfade bcfeag cedgb efgbc bd gdb | fedbcg cefdbg edcag dgb\r\n" + 
			"bfcga gfadb egcadf cbeagfd bdfeag bcfegd bd edgaf dbae dfb | bfgadec dfb bfd cbfga\r\n" + 
			"cbegad bacde eafdb bcgea cad cfgadb cd gced fgedcab bfecag | fdgbeac eacbdfg acdbe abdfe\r\n" + 
			"cebaf dcegbaf edfgab cabgfe bafeg cgaf aegbdc cea febdc ca | ac ac ca cfga\r\n" + 
			"db bfd ecdfg febdc debfac egdfab cadb efgacb defacgb bcfae | edbcagf bd debfac fdb\r\n" + 
			"egfdcab dcgbe dacgef fbad bafcge ab degfa ebfgad gbdea gab | ba fabgecd ab agedbf\r\n" + 
			"afbc efadc efcgabd dfbeac ac bdgeac cfgbed afgde cfdbe dac | ac fegcdb afedc facb\r\n" + 
			"acbde cgfdea cdbge cfbdea abd ab gacbdfe fdcgab acefd fabe | fbae dagefbc aefbcgd dba\r\n" + 
			"cedbfga dabecf afbge fab ecbfdg gedfba fbgde cbeag dfag af | beafg acbfed fa gdaf\r\n" + 
			"ecbgfa eab agdfebc adbg gdfbe dfgecb dabfe afced bagfde ab | bdag gdba dfbge gbda\r\n" + 
			"egfdac eadbg gfdab bcdega ae egabdfc cdfbeg dae gcbed bace | dgbefc gadefc ea eacb\r\n" + 
			"bgacf dgfcea cbfgeda fdab fa fag fbgadc gcbef cdabeg bcgda | gfa fag afg aedcbg\r\n" + 
			"egafdcb dcega dgfbc egdbaf ebdcg dbe eb cabdeg ceab fcdaeg | bed edcfag gadefc fcdbg\r\n" + 
			"dgcab afgb bcg dcaeb dbgcaf bg ecfdgba gafced fbcdeg cfdga | cefgdb gacfbed afdgc gbcdfa\r\n" + 
			"cgeda bafcg cgdfa dgfcae bceagd bdgface adf fd feabcd efdg | edfg gdeca cgbdfae dcfgaeb\r\n" + 
			"dbefga eadcgb gbaedcf daebc geadc bd bcgd bafec bad gaecfd | edagc abcef fceba aecdg\r\n" + 
			"bcegf cfbega bgedca befca gabecdf bgc gfca gebdf cg dcfabe | efdbg bcg acgf fecbadg\r\n" + 
			"gfcdeba dbaec ecgaf gde agedfb bcafeg gdfc dgeac dg cadefg | egfca fegac edg daebgf\r\n" + 
			"bea geadbf be efcad afecgb adfcbg cbefa fcabg gecb egbdfca | eab cbfae gbcafed cbeadgf\r\n" + 
			"adgfec bgea fbcdg be fdceba bgedc edb acged bfdceag dgbcea | decfba efcdba gbdface aedgfc\r\n" + 
			"ebgfc dcb dc ecfd fecbgd debcg bgfacd defcgab bacgef dgabe | dc adfbceg befgc cfed\r\n" + 
			"cbgdea gafc fcegd eagdfb bfdce egc efdag gdafec cg gfbceda | cg ceg dgfea cg\r\n" + 
			"fgdab egacbfd decgaf eaf edcfgb ea efdcb aefcbd eadbf abec | fdbea ea acefdb ea\r\n" + 
			"bdegcf aegfb eacd ec acfgd gfdeca fce adcfbg begafdc gcaef | acde gbefcd adce fec\r\n" + 
			"cdgfbe fdacg cegdf cebd cbeafdg efgcb aegcbf bgfead efd de | fcegdba cbde gbcadfe fed\r\n" + 
			"adfge efgbda gacfde gecfba ce feacd adbfc cgde fce dfabgce | fgcaed cgde gfaced fdeac\r\n" + 
			"ga cgaefdb dcebfg fbegac gbda acdef cdgeb gbaedc ecadg cag | bagd bgfdce cbgde dbfaecg\r\n" + 
			"gec efdabg bfgdcae dfebc agbc aefbg afgbce agecfd cg cegbf | acfdgbe cbdef abcg afbedcg\r\n" + 
			"ebcgaf cagdf afbdeg fbcda dcfbeag beacfd befca bfd bd cebd | dfb dbf feacbg gfdaecb\r\n" + 
			"ecgdfa afe gbafd bfgdac ae decbf dgebaf afbed abeg cedabfg | egab cgeabfd eadgbfc ebag\r\n" + 
			"agcfdeb fdabe gfcaeb edf cegfad badce gdbf fd gbafe fedbga | efd bfgd daecgfb cabefg\r\n" + 
			"bcdef gefcadb fdeba fdega ba bgea bgdafc efbagd dfcgae dba | ba ba edafb ebga\r\n" + 
			"acgbe age ga agebfd afcebg bdegc acfeb fgca cefdba cabegdf | ag eag afgc ag\r\n" + 
			"bacfd feb gfea dabge fegadbc adegbf fe aefdb cbdgae gdcbfe | ef ebdfcg fgdeba efb\r\n" + 
			"efgca fadbc daecgf ecbfgd gd daeg dfcagbe agfdc bafecg dgf | fgd cfeagb afegc dfg\r\n" + 
			"cgeabd cdagf dfab afcbgd dceafbg adgbc cfd df efdgcb afgec | cdf cfabdg abfd df\r\n" + 
			"bgacef acegf adgce cgbfaed dgbec gcdefa afed ad badcgf cda | ad egadc degbfac efcagd\r\n" + 
			"de ceafb bfedacg decf cbeda aed gdefab dfacbe gadcb gecbfa | acefb eda edcf ead\r\n" + 
			"fdebga dgbcef baf af caefb debcf baecg cgdfaeb dafc facdbe | af fdac fgceadb efcba\r\n" + 
			"dgbfac cabdefg ag gda dgcbf gafdb dfaeb bedgcf cafg adcebg | fgadb egfbcad fgca gfbda\r\n" + 
			"egcbfda fagbde cb egbcf fbc dcgb cdbafe fgdbe dcefbg ceafg | bfc gfcebda bcf bcf\r\n" + 
			"cfeba ag fecag feacgb eag defgc egabfd gbcafed agcb bdacef | gcfabe efcadgb ga gabc\r\n" + 
			"fgeadb gbfdace feacb ed bacfde fdbcg edca ecbdf cebgfa def | ebcfa acebdgf efd aced\r\n" + 
			"fcgad aegfdb ea bdfcea dea egfbd aebg cgbedaf begfdc fedag | geba egbfd fdagbe dcbaef\r\n" + 
			"faebdg efbca fadce bgefa fcbgae dbecfag bgcf cb ecb dgaecb | cadebgf ecb gcfb cb\r\n" + 
			"ecdf begca cbagfd ed edb dfebacg cdgfb bcedg cgbfed edagbf | de dfce gedcbf de\r\n" + 
			"badfec dfgcab gdbeafc cbf defc acefb ebdgaf cf abedf egacb | cdfe cdef fedc fc\r\n" + 
			"afcgbd fdcg fbc bgdac acbgef cf fcabd gdebca fbaed fcdageb | cf fbc cf gfcd\r\n" + 
			"cefba dbef fe gdafec dbcae fdebac bfadcge fec gceabd abcfg | gacedb debac bcaed gacfdbe\r\n" + 
			"bagfe db bdf gafdc bfgda cgefbd cafedg fcgabd fdeacbg dacb | gabdf cdgbfa bd egfbdc\r\n" + 
			"dbcaef bacfd begfcd gbafc cedbf da eabd gecdfa cda fbecgda | aedb cda efcdb adcfbe\r\n" + 
			"faebgd afcbdg gf dbacfeg gdf degfa dcfea gebda dgaceb gfbe | bgfe decaf gf edgfa\r\n" + 
			"fg aecgbfd ebcfa efabg adgeb fge egcbdf bedagf dbcaeg fagd | fg dfga gfda gafd\r\n" + 
			"cedgabf dcba ab cedaf bae cabefd fgaced fdaeb fabgec dfgeb | bedfac ab fecbga debfcag\r\n" + 
			"dec ce fcabde daefb fgabde aefc bdagc dcgfeb efcdabg caedb | gadbfec ecfa baefgd ce\r\n" + 
			"egcaf ed adbe afbcd dgfecba acbdgf edf decfab edcaf egbcfd | dgecfab eabdfcg fbdaec abed\r\n" + 
			"fea febcg eacgf ecda cdgfa agcedfb edgfba ae agcfde cbadgf | fcegb fgbaced dcafg caed\r\n" + 
			"gebfd ec dec eabc bafcged debgc fecgda fdgabc dcgabe cgadb | adegbcf gbacfed bfgcdea ce\r\n" + 
			"egfdab cad bcgad cd bcfga gfdbcea dgcaeb cedg defbac beagd | cadbfe decg cd cadfeb\r\n" + 
			"cge gadebfc fbcgae gc dagec agdeb dcefga cgdf afbecd ecfda | ebgcaf dbeag fgcdbae dgcf\r\n" + 
			"bdegca ecbdaf bfae cgdfb ebafdgc ba dfabc bac feagcd fdcea | bac ebcfadg ba ab\r\n" + 
			"gceda eab agbfedc eafcdg ab gbac bgdef gacbed dgbea cfaebd | bgfcade ba abgc bae\r\n" + 
			"gbfe eg gcfdeb daebcg bcdef cgefd cdfag fbdeac egcadfb gec | ge befgdc gce egc\r\n" + 
			"bga gbeacd cbgde gdefcab cagbdf ab beda cfbegd aecgf agecb | agfce daeb bead agb\r\n" + 
			"fedgba agecbf bc bcdf cgb febdcg ecgfabd gcade dgbec efgdb | bgedfac cb bc bc\r\n" + 
			"gfaeb bcdfe cea gfca bdacge aecbdfg fegbca ac dfgbae caefb | cfga agcf cfga cbgade\r\n" + 
			"cgeab fbegda adb dagbfec eafgbc bd bgcd adecf cegdba daebc | efbcdag gdcb bd deacfgb\r\n" + 
			"bcagd cbgde gbfad eabdgf cda ceabfd fcag cdgabf agcbdfe ca | ca ca ac cda\r\n" + 
			"agdce dageb eacdbf ecgdaf bafgd dgfacbe dgbaec bceg bea be | gceb bgdcaef edgac aedgc\r\n" + 
			"bceadf adcfeg ga dfag geacd cabegdf ceafgb gca aecfd edcbg | ag acbfde gdfa ag\r\n" + 
			"fdcegb abdfce cdega bgcad gfea cae ae bcafged fagedc fgdec | gfdce bafcde gfcdeb ae\r\n" + 
			"egbcda cebadf bfe ef bedcg bgfda fgec fedbg begcfad gdbcef | bef gcbeda ef ecgf\r\n" + 
			"egfdb cgd gdbfea cgbdf fdcba fgbdaec agbdec cg fecg cgdbfe | abgfedc fbcgd cegf gc\r\n" + 
			"cfbegd ab gbacfd agefd ceab baf acfgdeb eafbgc bfegc bagef | ba abf beca baf\r\n" + 
			"fcega fecda cbgaef fgaedb gbfc geadbc fag ecbdgaf eacgb fg | ecdfa bfgc dgebaf gcabe\r\n" + 
			"aefdcg gfdcab ab fagb dbaegcf adb faecbd gcadb fcgad ebdgc | gadcef bfdaecg fgba fgecad\r\n" + 
			"eabfc cdebf bgfca ecfbgad fbdceg aeb gceabd afde ea cbfade | abcdgef eabfc abe bdcfae\r\n" + 
			"cd dgfceab dec cbegfd gedaf adfgec ecbag fgadbe cadf cgdea | fgedbc edc dce adcgfeb\r\n" + 
			"agcdbf ecdgf adfcg agfcbed ef ecdfab efc agfe cgebd defagc | eadcfb cfdbega cef geaf\r\n" + 
			"cdbafe deacf ga dbecag fecdagb bcfge gfda caedfg gae eagfc | ga eag gfcebad cfgae\r\n" + 
			"ceadfg bdf gfbec adbe fbegd abgdfc bd gdefa gdfeba dgacefb | bagedfc facedgb fbd bd\r\n" + 
			"ecdba ecdagb gcfeda fbdaeg cfedb befcdag ca agedb abcg ace | cea fbaged eca bcag\r\n" + 
			"gfedc gdaf gaefc dcf bgcde gdeafc fcgadeb gecabf cdafbe df | fd gcaef df egfdca\r\n" + 
			"afegb cefgb eadcbg cgfbad dbecg cfde aegbcdf fcb gdebfc fc | fbcged bcf gbced aefgdcb\r\n" + 
			"ca bfeda cae ecdfgb edgcf dgceaf gbaced afcg gbeacdf aefcd | ac dfcebag cagf ace\r\n" + 
			"begcad efdagb fbgeadc fedcba aec abedg fcegb ca cgda abegc | abcfgde fgebadc bagedf gdca\r\n" + 
			"aegb cfeadb acdgf decgbf ecgdb gcfedab bgdcea ba gbdac bad | bgceda gdafc fgadc dafcbe\r\n" + 
			"fgecba aec adfe deacb ae abegdcf bcdag ecbfd gecdbf dafceb | gdcab ae edfa daef\r\n" + 
			"agfde eacg adebfg fdgcb cafdg fgdeca cfbaed ac gcadfbe acf | fca gdfae acge dbeacf\r\n" + 
			"fbagce de deg edfa fcbdg gbdcae aegfb dbgfe dgacbfe gefbad | edfa de ged bdgcf\r\n" + 
			"bacfdg cdeafgb aecbfd daefb agedc afc abdefg cebf deacf cf | gbdafc efbc cfead fc\r\n" + 
			"cegfab cdag bdc dc febad gfdbce dcaefbg gfbac dcafbg cdabf | dgafcb cdag dcbgeaf gaefdbc\r\n" + 
			"dagefb acefg afdcb gd dgfaecb facbgd bcdg gda badcfe fgacd | fbecgda cfadb gbdc fcaeg\r\n" + 
			"gfbd cfeda gabcfe db dba baefg beafd bcgdae fedcbag efbdag | dba bad ebgaf dbegfca\r\n" + 
			"cf fbdega aefbg afbcedg edgca dgbcfe gfaec febcag ecf afcb | aefbg gcaefb dbcegaf cef\r\n" + 
			"gebdaf abgd cdabfe gacefbd agfed dg ged fegac aebfd fbdcge | egd abdegcf gadb afbgde\r\n" + 
			"gcdf efdcb acfebd beagdc gd bdefg ebfcgd bdg fbega geabcfd | dgceba ecadgb adgfbce edbgf\r\n" + 
			"fdcaebg efbdgc bgdacf dcae cbdge gebaf abd edgab da bgaedc | cebagd ad da gebcda\r\n" + 
			"bfgcae cbd bcfea aedcb gbefcd cd adecfb adfc cagdfbe dbega | fcad cdabgfe dacf dcb\r\n" + 
			"becg gfbace egacfbd acbfg acdfgb aec ce bdfea cgedaf fbcea | afcbg fedagc befac aecfgd\r\n" + 
			"befa cgfed cfadb gdcfba acdfe ace edgbac fgcdbea ceafdb ae | feab eacdf bcdgafe bfae\r\n" + 
			"bcdefag dafeg fgec dgeafc gea gdfbca cgdaeb bfdea eg dgafc | eg aefcdgb ecdfag gea\r\n" + 
			"gfadb gedfb bdecgfa adbefg dcgaf abf gdcefb aebg ab bafced | gdbfe gbfed ebag cabgfde\r\n" + 
			"dbegfa acgbdf eg gcfbed gdafb bfacegd ebg aged fageb bacfe | ge gefcdba ge bgdfa\r\n" + 
			"adbgec fdceg afdgc bedf cfe fe gaecbdf faebgc gcbed fcbedg | acfdg gdeabc debf ef\r\n" + 
			"dfgbe da ecbadg dafeg fadb gfdbea dagcfbe dbgfec cfgea dga | befdg agd eafdg fabd\r\n" + 
			"bedc aefbc ceadfb fdb cbagfed fecgab dcfba gaebdf cfagd bd | db bfd bfcae adgefb\r\n" + 
			"cabedgf edbfgc gedcfa bacegf fde dgbf baced fd fedbc fegbc | fcdeb adbgefc adecgbf fde\r\n" + 
			"fged egafc ecafgd efcda dfa dfcabg becfag fd agcdfbe ecdab | dgef fd dgfe df\r\n" + 
			"deafgb gbfdc dg dbg agdc dbcafg cadfb bafced fgbce daegbfc | gbfdac afdgcb dgb gdcfb\r\n" + 
			"dfecb fabdge cegbaf fdca faecb aefcdb bdf gdbce afgcebd fd | dbf fbd edcabgf fadc\r\n" + 
			"cbf egabfdc fdcgba fabecd cf dfagb gfac adbegf cdegb bcfgd | acfg afgbed cgdfab fc\r\n" + 
			"ebdfagc bcfa gecfdb af daegbf fgcbd bcdfag egcda fgdca gfa | fcab gafcdb dbfega acfb\r\n" + 
			"adfbg eacb abfcg ecafbg gface ecfgad bc degfbc cbg gabdefc | cb gabefcd cagfebd agcbfe\r\n" + 
			"ebgad bef gacfe gceadf fb efcagbd eabfcd cegfab gbfc ebagf | cefadg fcgae fgbc ebacdgf\r\n" + 
			"dcg aged efacd cfgdae edacbf edfcg bcagdef gd fecbg dcfbag | cdeabgf gd gcd adge\r\n" + 
			"fecgda cdabefg gabfc gfacbd ecgabf cbafe bcdef ea ace bgea | egba gabe beagfdc fbcag\r\n" + 
			"bacdgf eafbd cagde becad afgcedb bc fbegad cfbe cba fcdaeb | fcbe abc dbecaf edcba\r\n" + 
			"ecbdf dagfceb abcfde cbade aebgc adb bfgead debfgc cafd ad | bad dcaf begac da\r\n" + 
			"gbae deafb fdgbea cegfd ag cdaefb cgabefd degfa dag dfcgab | baeg agefdb bcdafg cbdfga\r\n" + 
			"ebcdf gdeafbc cdgbfe eaf cdfa deafbc gedab aecfbg ebadf af | befacd ebfda af af\r\n" + 
			"cfab dcb gfedbac bc cgaedb fbdge cbfdg acgbdf gdcaf acgdfe | bc fcab afcb dbc\r\n" + 
			"edbag gebdc bedfag eacdbg bcedgfa ecab ec gaedcf bdcfg cge | ce ceab ec bdegcaf\r\n" + 
			"fedcba fbgca cbafge ef gbdacf efbgcad gefca befg ecdga afe | fea adgfcb fcage bcdfae\r\n" + 
			"da edfgc cgfdba adfecgb gfaeb dcefga fecdgb gad gdeaf ecad | cedfg dbfcega gfcaed abdcfeg\r\n" + 
			"fcgbed fcbedga cafbe gdceba bcafge bfe fagb fb eacbg facde | cdfbeg fb ebgcadf fgcedba\r\n" + 
			"fecdb fdcbae afdeg cdfea aec efgbadc ca cbfa dcagbe gbefdc | facb fabc fcdbega efadcb\r\n" + 
			"dagcf gadfeb ad dfegbc cgdfba dbac abgecfd dcgfb cfgae adf | fgadc cbda ad cfagd\r\n" + 
			"fdgcb agedbc cdbef feac efb ef febdac afdegb abced edgafcb | fe bfecd dcebf cafe\r\n" + 
			"cafdbg eaf fe gebf befcag cfbga cgaef fdaecbg edcga febacd | gfbe egbf aebgcdf faceg\r\n" + 
			"bfc febg cdfga dafbec cegbd fb cdbgfe cbfgd cadegb gbefdac | dcefgb dbfcg deafbc gbdec\r\n" + 
			"bacgd cedfbg cf aefc dfc aecgdf adfcgeb gafed cafgd baefdg | dgafc fc dfc gfbced\r\n" + 
			"bcge fgc efcdab ecgafb cfeab fabgd fagbc bgcdeaf efcagd cg | cbeg bdagf bcgaf fgc\r\n" + 
			"cdaeg fbaec ceafd fd cfd adfceb bgaecf dbcegf bdaf cgeabdf | df cdf fdagbce cbedaf\r\n" + 
			"dgcbf gecfadb gbdecf gfdbca fbagde ad cdgab dag afcd caegb | cgbedf dfac bdacfeg cafdbg\r\n" + 
			"defacg bd cdbegaf cfaeb dbf cbefgd cgdaf cfbgda facbd gabd | gfadec bdf gbad adgcef\r\n" + 
			"bagdce deagcf bcdf cfa abgfdc cf geacfbd afgeb bacfg dcagb | cf cdbf aedcbfg fbdc\r\n" + 
			"fbceda bedcf dg gefcdb aefbgd gdf cgde gfacb bdcfg dfcegab | dfbagec dfg cabdfeg gd\r\n" + 
			"gda cfgbde dgbfc fgabdc fcagd cagfe baecgdf faebdg ad adbc | efgdcb dag dacb ad\r\n" + 
			"dagec eca fgeacd cdefab gfdacbe afged dabgfe ac dcebg afgc | bdgce ecdga eagdc ca\r\n" + 
			"da bda gadc dbafg fabgc faedcb dfcbgea agcfbe bedgf gfbadc | acbfegd gfabdc bad da\r\n" + 
			"gdce aebfdg faebc fcdabg acgfedb eacgdb adc adgeb cd daceb | cd cgbadf cad edcg\r\n" + 
			"caedb agbed gfbcad eca fdce gbface baefdcg acfdb fbedca ce | eac cebadf fagcdb cae\r\n" + 
			"fcbadeg fcae bfdca ceb ec cdebaf ecbdf gcedab bgdfe dfagcb | geabcd acef agbced gdbeafc\r\n" + 
			"cbdef degba efbad eafc dbcfge ecdafb af fcbgad edfbcag baf | gbdea ecaf eacf egfcdab\r\n" + 
			"fcde gabfd cdgeba fac fc dcabe bfaced dacbf ebgfac cgfbeda | adcfb fca gebdfca fadbc\r\n" + 
			"fdceg cbd edcgfa cb cdgfeb gdebac edgcafb fceb agbdf bgcfd | adfgce beacdgf bc cedfbag\r\n" + 
			"fcbegda dbcefg gcebad gbfc cdb gfbaed bc cebfd ecdfa bdegf | dcb gabedf beafdg bc\r\n" + 
			"cfaed bacdg egd aegcdf gcef gedac cdbefga afdgbe bdfaec eg | gcade cefad bdgfea gaedc\r\n" + 
			"ac dbeacg ace defba fcaebdg dabfce abfedg dcaf cbfae efbcg | fegdba cfbae cea dbacfe\r\n" + 
			"dgcaef bgcaef cfdgeb gd fgecb dcgfbae gebfd bgdc adebf fdg | gdf dafgbec dfg adfeb\r\n" + 
			"ag cadg cgfed aefbc bcegdf agdfbe agf egfca gefcdba gdfeac | dgca dgcaef cdga adebcfg\r\n" + 
			"dfcgbe badg aedgc eabfc dfcbega cgdafe eagbc aegcdb gbe bg | acfdge bge adbg bg\r\n" + 
			"bcfage adcefg dfagc fgaec cdf abcgd dcbgafe df dfae fgcbed | df cfd eacgf fdc\r\n" + 
			"cbadfe bfg cfgebd cbdg edfgbca bg egbdf feagcb fedag cefdb | fgdeb gfb fbdeca bgcd\r\n" + 
			"bcde fdcgba abfcde dbfac abefc befga aec ce acfdge afdegbc | fcbad eac ec becd\r\n" + 
			"ce cbagdf gdcbf cfaebdg gcbe acfdge fcdegb dbaef cef bcefd | acbgdfe cfebd gcebdf ec\r\n" + 
			"ecgbd eacbf cgadeb edf ecafbdg afgebd dcgf ecfbd fgcbed fd | bcfea edf efgabcd df\r\n" + 
			"dgbf egafcd cfedbag cgb abcef dcefg ebcfg cgeadb bg cgbfed | adfgebc egbcf cfeab gaefbdc\r\n" + 
			"gecbfd gbedc dgabcef baefgd abegc dbfce bfdace dg gdb dfcg | dfgc bedfac dg dbg\r\n" + 
			"acbdgf daeb gcbeaf gdfce adefg fbdga cbdfega fae fgdbea ea | ea fcdgaeb ae gecfadb\r\n" + 
			"ga bdgafe bcaed dcgaebf bacfed acedg gbadec edgfc agd bcga | adg dga agcb fdecgab\r\n" + 
			"ebdcfg cfbdg ecgabf gcd edfc fdcbeag ebcdag dabgf dc cgebf | cd gcd gacbde cd\r\n" + 
			"egdfac bf afebdg baf bfdeca bgdf fedga egacb gecbafd gbeaf | bfa fb gfdb fb\r\n" + 
			"gcafb dc gecd fecbgda cfdbg dfebgc cdf fbegd dcfbea fgedab | edfcbg dgbcf cd dcf\r\n" + 
			"badf fcagd agcefb dfgcab ba dcbeg cedabgf abgdc acegfd abg | ebfagc dfab ab fcbdeag\r\n" + 
			"cfgbad gdbfe fe efgdacb cbdge dabgfe egdcaf befa bdgfa egf | decagfb eabf baef fe\r\n" + 
			"dbfea bcaefdg dcfgeb ag bfceag egfcda becfg fgeba gcab ega | agbfecd aedfcgb gcba ga";
}
