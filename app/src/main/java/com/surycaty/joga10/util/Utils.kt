package com.surycaty.joga10.util

import android.content.Context
import android.widget.Toast

/**
 * Created by negus on 30/09/17.
 */

object Utils {

    fun mensagem(context: Context?, texto: String): Toast {
        return Toast.makeText(context, texto, Toast.LENGTH_LONG)
    }

}
