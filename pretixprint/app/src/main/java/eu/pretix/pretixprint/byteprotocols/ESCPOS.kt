package eu.pretix.pretixprint.byteprotocols

import androidx.fragment.app.Fragment
import eu.pretix.pretixprint.R
import eu.pretix.pretixprint.ui.SetupFragment
import java8.util.concurrent.CompletableFuture
import java.io.InputStream
import java.io.OutputStream


class ESCPOS : StreamByteProtocol<ByteArray> {
    override val identifier = "ESC/POS"
    override val nameResource = R.string.protocol_escpos
    override val defaultDPI = 200
    override val demopage = "demopage.txt"

    override fun allowedForUsecase(type: String): Boolean {
        return type == "receipt"
    }

    override fun convertPageToBytes(img: ByteArray, isLastPage: Boolean, previousPage: ByteArray?): ByteArray {
        return img
    }

    override fun send(pages: List<CompletableFuture<ByteArray>>, istream: InputStream, ostream: OutputStream) {
        for (f in pages) {
            ostream.write(f.get())
            ostream.flush()
        }
   }

    override fun createSettingsFragment(): SetupFragment? {
        return null
    }
}