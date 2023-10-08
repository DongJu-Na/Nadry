
import { createI18n } from 'vue-i18n'
import ko from '@/locales/ko.json'
import en from '@/locales/en.json'
import chs from '@/locales/chs.json'
import cht from '@/locales/cht.json'
import jp from '@/locales/jp.json'
import de from '@/locales/de.json'
import fr from '@/locales/fr.json'
import es from '@/locales/es.json'
import py from '@/locales/py.json'

export const i18n = new createI18n({
    locale: 'Kor',
    fallbacklocale: 'Kor', 
    messages: {
    	'Kor': ko,
        'Eng': en,
        'Chs': chs,
        'Cht': cht,
        'Jpn': jp,
        'Ger': de,
        'Fre': fr,
        'Spn': es,
        'Rus': py,
    }
})